package fr.formation.produitservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient(value = "commentaire-service", url = "http://localhost:8082", path = "/api/commentaire")
// @FeignClient(value = "commentaire-service", path = "/api/commentaire")
@FeignClient(value = "commentaire-service", path = "/api/commentaire", fallback = CommentaireFeignClient.Fallback.class)
public interface CommentaireFeignClient {
    @GetMapping("/note-by-produit-id/{produitId}")
    public int getNoteByProduitId(@PathVariable String produitId);

    @Component
    public static class Fallback implements CommentaireFeignClient {
        @Override
        public int getNoteByProduitId(String produitId) {
            return -5;
        }
    }
}
