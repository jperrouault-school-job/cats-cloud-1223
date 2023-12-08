package fr.formation.queryservice.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentaireResponse {
    private String id;
    private String text;
    private int note;
    private int noteQualite;
    private int noteQualitePrix;
    private int noteFacilite;
    private String produitId;
    private String produitName;
}
