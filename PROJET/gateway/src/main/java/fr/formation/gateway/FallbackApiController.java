package fr.formation.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/demo-fallback")
public class FallbackApiController {
    @GetMapping
    public Mono<String> demo() {
        return Mono.just("Fallback ...");
    }
}
