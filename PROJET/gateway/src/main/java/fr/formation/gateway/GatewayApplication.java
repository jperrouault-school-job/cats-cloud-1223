package fr.formation.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            // Route vers Query Service (requêtes HTTP GET)
            .route(r -> 
                r   .method(HttpMethod.GET)
                    .filters(f ->
                        // Nécessite d'avoir un CircuitBreaker (par exemple Resilience4J)
                        f.circuitBreaker(cb -> cb.setFallbackUri("forward:/demo-fallback"))
                    )
                    .uri("lb://query-service")
            )

            // Route vers Produit Service (requêtes /api/produit/** */)
            .route(r -> 
                r   .path("/api/produit/**")
                    .uri("lb://produit-service")
            )

            // Route vers Commentaire Service (requêtes /api/commentaire/** */)
            .route(r -> 
                r   .path("/api/commentaire/**")
                    .uri("lb://commentaire-service")
            )
        
            .build()
        ;
    }
}
