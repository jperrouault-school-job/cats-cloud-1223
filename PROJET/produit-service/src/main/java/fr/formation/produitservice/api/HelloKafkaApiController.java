package fr.formation.produitservice.api;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/hello-kafka")
@RequiredArgsConstructor
public class HelloKafkaApiController {
    private final StreamBridge streamBridge;

    @GetMapping
    public String hello() {
        this.streamBridge.send("demo", "Le message démo");
        
        return "OK";
    }
}
