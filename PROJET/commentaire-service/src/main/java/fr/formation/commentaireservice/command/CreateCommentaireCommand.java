package fr.formation.commentaireservice.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class CreateCommentaireCommand {
    private String commentaireId;
    private String produitId;
}
