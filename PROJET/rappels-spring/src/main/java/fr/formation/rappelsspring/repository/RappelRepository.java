package fr.formation.rappelsspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.rappelsspring.model.Rappel;

public interface RappelRepository extends JpaRepository<Rappel, String> {
    public Optional<Rappel> findByName(String name);
}
