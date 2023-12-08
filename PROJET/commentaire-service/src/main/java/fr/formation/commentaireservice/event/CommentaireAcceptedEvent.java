package fr.formation.commentaireservice.event;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentaireAcceptedEvent {
    private String commentaireId;
}
