package fr.formation.commentaireservice.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Commentaire {
    @Id
    @UuidGenerator
    private String id;

    private String text;
    private int noteQualite;
    private int noteQualitePrix;
    private int noteFacilite;

    @Enumerated(EnumType.STRING)
    private State state;

    private String produitId;

    public enum State {
        WAITING, OK;
    }
}
