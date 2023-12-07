package fr.formation.bulkheadtester;

import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BulkheadTesterApplication {
    public static void main(String[] args) {
        SpringApplication.run(BulkheadTesterApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        RestTemplate restTemplate = new RestTemplate();

        return args -> {
            IntStream.range(0, 10).parallel().forEach(i -> {
                String result = restTemplate.getForObject("http://localhost:8081/api/hello-bulkhead", String.class);
                System.out.println(result);
            });
        };
    }
}
