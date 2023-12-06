package fr.formation.produitservice.model;

import java.math.BigDecimal;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Produit {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "name")
    private String name;

    private BigDecimal price;
    private boolean notable;
}
