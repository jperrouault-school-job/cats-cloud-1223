package fr.formation.commentaireservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.commentaireservice.model.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, String> {
    // @Query("select c from Commentaire c where c.produitId = ?1")
    public List<Commentaire> findAllByProduitId(String produitId);
}
