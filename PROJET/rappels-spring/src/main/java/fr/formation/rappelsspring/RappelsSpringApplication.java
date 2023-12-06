package fr.formation.rappelsspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RappelsSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(RappelsSpringApplication.class, args);
    }

    // @Bean // Dit à SPRING d'exécuter cette méthode et de garde dans son contexte l'instance
    // RappelApiController rappelApiController() {
    //     return new RappelApiController();
    // }
}
