package fr.formation.produitservice.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@RestController
@RequestMapping("/api/hello-bulkhead")
public class HelloBulkheadApiController {
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public String helloBulkheadFallback(BulkheadFullException e) {
        return "KO";
    }

    @GetMapping
    @Bulkhead(name = "hello", fallbackMethod = "helloBulkheadFallback")
    public String hello() throws InterruptedException {
        Thread.sleep(1000);

        return "OK";
    }
}
