package fr.formation.queryservice.event;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentaireCreatedEvent {
    private String id;
    private String text;
    private int noteQualite;
    private int noteQualitePrix;
    private int noteFacilite;
    private String produitId;
}
