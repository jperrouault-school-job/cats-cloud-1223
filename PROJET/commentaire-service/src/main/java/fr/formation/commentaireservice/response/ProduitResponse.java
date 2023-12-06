package fr.formation.commentaireservice.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProduitResponse {
    private String id;
    private String name;
    private boolean notable;
}
