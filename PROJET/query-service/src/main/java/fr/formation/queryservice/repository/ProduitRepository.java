package fr.formation.queryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.queryservice.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, String> {
    
}
