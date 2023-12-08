package fr.formation.queryservice.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Produit {
    @Id
    private String id;

    private String name;
    private BigDecimal price;
    private int note;

    @OneToMany(mappedBy = "produit")
    private List<Commentaire> commentaires;
}
