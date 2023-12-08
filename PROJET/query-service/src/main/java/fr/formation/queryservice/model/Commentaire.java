package fr.formation.queryservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Commentaire {
    @Id
    private String id;

    private String text;
    private int noteQualite;
    private int noteQualitePrix;
    private int noteFacilite;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;
}
