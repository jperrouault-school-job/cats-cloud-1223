package fr.formation.produitservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.produitservice.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, String> {
    
}
