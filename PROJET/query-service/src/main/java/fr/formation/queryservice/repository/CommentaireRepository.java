package fr.formation.queryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.queryservice.model.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, String> {
    // @Query("select c from Commentaire c where c.produit.id = ?1")
    public List<Commentaire> findAllByProduitId(String produitId);
}
