package fr.formation.produitservice.command;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class CreateProduitCommand {
    private String id;
    private String name;
    private BigDecimal price;
}
