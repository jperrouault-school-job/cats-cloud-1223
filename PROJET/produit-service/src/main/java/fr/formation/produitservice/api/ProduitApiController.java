package fr.formation.produitservice.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.produitservice.model.Commentaire;
import fr.formation.produitservice.model.Produit;
import fr.formation.produitservice.repository.CommentaireRepository;
import fr.formation.produitservice.repository.ProduitRepository;
import fr.formation.produitservice.request.CreateProduitRequest;
import fr.formation.produitservice.response.ProduitResponse;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/produit")
@RequiredArgsConstructor
public class ProduitApiController {
    private final ProduitRepository repository;
    private final CommentaireRepository commentaireRepository;

    @GetMapping
    public List<ProduitResponse> findAll() {
        return this.repository.findAll().stream()
            .map(p -> {
                List<Commentaire> commentaires = this.commentaireRepository.findAllByProduitId(p.getId());
                int note = (int)commentaires.stream()
                    // .mapToInt(c -> c.getNote())
                    // .mapToInt(c -> {
                    //     return c.getNote();
                    // })
                    .mapToInt(Commentaire::getNote)
                    .average()
                    .orElse(-1);

                return ProduitResponse.builder()
                    .id(p.getId())
                    .name(p.getName())
                    .price(p.getPrice())
                    .note(note)
                    .build();
            }).toList()
            ;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody CreateProduitRequest request) {
        Produit produit = new Produit();

        BeanUtils.copyProperties(request, produit);

        this.repository.save(produit);

        return produit.getId();
    }
}
