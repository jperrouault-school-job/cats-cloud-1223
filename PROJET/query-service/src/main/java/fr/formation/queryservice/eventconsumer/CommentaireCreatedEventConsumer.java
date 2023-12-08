package fr.formation.queryservice.eventconsumer;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import fr.formation.queryservice.event.CommentaireCreatedEvent;
import fr.formation.queryservice.model.Commentaire;
import fr.formation.queryservice.model.Produit;
import fr.formation.queryservice.repository.CommentaireRepository;
import fr.formation.queryservice.repository.ProduitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component("onCommentaireCreated")
@RequiredArgsConstructor
@Log4j2
public class CommentaireCreatedEventConsumer implements Consumer<CommentaireCreatedEvent> {
    private final CommentaireRepository repository;
    private final ProduitRepository produitRepository;

    @Override
    @Transactional
    public void accept(CommentaireCreatedEvent evt) {
        log.debug("Commentaire {} created ...", evt.getId());
        
        Optional<Produit> optProduit = this.produitRepository.findById(evt.getProduitId());

        if (optProduit.isEmpty()) {
            return;
        }

        Commentaire commentaire = new Commentaire();
        Produit produit = optProduit.get();

        BeanUtils.copyProperties(evt, commentaire);
        commentaire.setProduit(produit);

        this.repository.save(commentaire);

        List<Commentaire> commentaires = this.repository.findAllByProduitId(evt.getProduitId());
        int note = (int)commentaires.stream()
            .mapToInt(c -> (c.getNoteQualite() + c.getNoteQualitePrix() + c.getNoteFacilite()) / 3)
            .average()
            .orElse(-1);
        
        produit.setNote(note);
        this.produitRepository.save(produit);
    }
}
