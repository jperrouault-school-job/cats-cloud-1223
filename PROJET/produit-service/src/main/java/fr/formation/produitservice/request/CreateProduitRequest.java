package fr.formation.produitservice.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateProduitRequest {
    private String name;
    private BigDecimal price;
    private boolean notable;
}
