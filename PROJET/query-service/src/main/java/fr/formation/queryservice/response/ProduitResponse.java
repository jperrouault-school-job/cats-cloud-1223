package fr.formation.queryservice.response;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ProduitResponse {
    private String id;
    private String name;
    private BigDecimal price;
    private int note;
}
