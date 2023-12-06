package fr.formation.commentaireservice.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateCommentaireRequest {
    private String text;
    private int noteQualite;
    private int noteQualitePrix;
    private int noteFacilite;
    private String produitId;
}
