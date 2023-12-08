package fr.formation.queryservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Les autorisations
        http.authorizeHttpRequests(authorization -> {
            authorization.requestMatchers("/**").authenticated();
        });

        // Valider le Jeton JWT auprès du serveur d'authentification Keycloak
        http.oauth2ResourceServer(server ->
            server.jwt(Customizer.withDefaults())
        );

        return http.build();
    }
}
