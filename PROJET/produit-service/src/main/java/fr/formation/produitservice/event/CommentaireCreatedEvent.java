package fr.formation.produitservice.event;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentaireCreatedEvent {
    private String produitId;
    private String commentaireId;
}
