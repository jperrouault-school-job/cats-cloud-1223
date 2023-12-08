package fr.formation.commentaireservice.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.formation.commentaireservice.command.CreateCommentaireCommand;
import fr.formation.commentaireservice.exception.NotFoundException;
import fr.formation.commentaireservice.model.Commentaire;
import fr.formation.commentaireservice.model.Commentaire.State;
import fr.formation.commentaireservice.repository.CommentaireRepository;
import fr.formation.commentaireservice.request.CreateCommentaireRequest;
import fr.formation.commentaireservice.response.CommentaireResponse;
import fr.formation.commentaireservice.response.ProduitResponse;
import fr.formation.commentaireservice.service.KafkaWaiterService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Sinks;

@RestController
@RequestMapping("/api/commentaire")
@RequiredArgsConstructor
public class CommentaireApiController {
    private final CommentaireRepository repository;
    private final RestTemplate restTemplate;
    private final CircuitBreakerFactory circuitBreakerFactory;
    private final StreamBridge streamBridge;
    private final KafkaWaiterService kafkaWaiterService;
    
    @GetMapping("/{id}")
    public CommentaireResponse findById(@PathVariable String id) {
        Commentaire commentaire = this.repository.findById(id).orElseThrow(NotFoundException::new);
        // Produit produit = this.produitRepository.findById(commentaire.getProduitId()).orElseThrow(NotFoundException::new);
        // ProduitResponse produit = this.restTemplate.getForObject("http://localhost:8081/api/produit/native/" + commentaire.getProduitId(), ProduitResponse.class);
        // ProduitResponse produit = this.restTemplate.getForObject("lb://produit-service/api/produit/native/" + commentaire.getProduitId(), ProduitResponse.class);
        
        ProduitResponse produit = this.circuitBreakerFactory.create("produitService").run(
            () -> this.restTemplate.getForObject("lb://produit-service/api/produit/native/" + commentaire.getProduitId(), ProduitResponse.class)
            ,
            t -> ProduitResponse.builder().name("not found").build()
        );
        
        CommentaireResponse resp = new CommentaireResponse();

        BeanUtils.copyProperties(commentaire, resp);

        resp.setNote((commentaire.getNoteQualite() + commentaire.getNoteQualitePrix() + commentaire.getNoteFacilite()) / 3);
        resp.setProduitName(produit.getName());

        return resp;
    }

    @GetMapping("/note-by-produit-id/{produitId}")
    public int getNoteByProduitId(@PathVariable String produitId) {
        List<Commentaire> commentaires = this.repository.findAllByProduitId(produitId);

        return (int)commentaires.stream()
            .filter(c -> c.getState() == State.OK)
            .mapToInt(c -> (c.getNoteQualite() + c.getNoteQualitePrix() + c.getNoteFacilite()) / 3)
            .average()
            .orElse(-1);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody CreateCommentaireRequest request) {
        // Optional<Produit> optProduit = this.produitRepository.findById(request.getProduitId());

        // if (optProduit.isEmpty() || !optProduit.get().isNotable()) {
        //     throw new BadRequestException();
        // }
        // ProduitResponse produit = this.restTemplate.getForObject("http://localhost:8081/api/produit/native/" + request.getProduitId(), ProduitResponse.class);
        // ProduitResponse produit = this.restTemplate.getForObject("lb://produit-service/api/produit/native/" + request.getProduitId(), ProduitResponse.class);
        
        // ProduitResponse produit = this.circuitBreakerFactory.create("produitService").run(
        //     () -> this.restTemplate.getForObject("lb://produit-service/api/produit/native/" + request.getProduitId(), ProduitResponse.class)
        //     ,
        //     t -> ProduitResponse.builder().build()
        // );

        // if (produit == null || !produit.isNotable()) {
        //     throw new BadRequestException();
        // }

        Commentaire commentaire = new Commentaire();

        BeanUtils.copyProperties(request, commentaire);
        commentaire.setState(State.WAITING);

        this.repository.save(commentaire);

        this.streamBridge.send("commentaire.created", CreateCommentaireCommand.builder()
            .commentaireId(commentaire.getId())
            .produitId(request.getProduitId())
            .build()
        );

        Boolean result = this.kafkaWaiterService.getWaiter().asMono().block();

        this.kafkaWaiterService.setWaiter(Sinks.one());

        return commentaire.getId();
    }
}
