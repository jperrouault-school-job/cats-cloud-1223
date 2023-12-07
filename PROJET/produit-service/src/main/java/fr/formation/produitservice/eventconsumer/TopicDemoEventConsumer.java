package fr.formation.produitservice.eventconsumer;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component("onTopicDemo")
@Log4j2
public class TopicDemoEventConsumer implements Consumer<String> {
    @Override
    public void accept(String evt) {
        log.debug("Message reçu (instance classe) : {}", evt);
    }
}
