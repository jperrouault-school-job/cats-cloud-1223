package fr.formation.queryservice.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.queryservice.repository.ProduitRepository;
import fr.formation.queryservice.response.ProduitResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/produit")
@RequiredArgsConstructor
public class ProduitApiController {
    private final ProduitRepository repository;

    @GetMapping
    public List<ProduitResponse> findAll() {
        return this.repository.findAll().stream()
            .map(p -> ProduitResponse.builder()
                    .id(p.getId())
                    .name(p.getName())
                    .price(p.getPrice())
                    .note(p.getNote())
                    .build()
            ).toList()
            ;
    }
}
