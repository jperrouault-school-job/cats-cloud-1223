package fr.formation.produitservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient(value = "commentaire-service", url = "http://localhost:8082", path = "/api/commentaire")
@FeignClient(value = "commentaire-service", path = "/api/commentaire")
public interface CommentaireFeignClient {
    @GetMapping("/note-by-produit-id/{produitId}")
    public int getNoteByProduitId(@PathVariable String produitId);
}
