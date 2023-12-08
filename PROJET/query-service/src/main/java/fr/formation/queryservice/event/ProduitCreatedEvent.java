package fr.formation.queryservice.event;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProduitCreatedEvent {
    private String id;
    private String name;
    private BigDecimal price;
}
