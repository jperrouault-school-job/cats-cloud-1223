package fr.formation.commentaireservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.commentaireservice.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, String> {
    
}
