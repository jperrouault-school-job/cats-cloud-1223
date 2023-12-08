package fr.formation.commentaireservice.service;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Sinks;

@Service
@Getter @Setter
public class KafkaWaiterService {
    private Sinks.One<Boolean> waiter = Sinks.one();
}
