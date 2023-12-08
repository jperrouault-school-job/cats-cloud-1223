package fr.formation.commentaireservice.eventconsumer;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import fr.formation.commentaireservice.event.CommentaireRejectedEvent;
import fr.formation.commentaireservice.repository.CommentaireRepository;
import fr.formation.commentaireservice.service.KafkaWaiterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Sinks;

@Component("onCommentaireRejected")
@RequiredArgsConstructor
@Log4j2
public class CommentaireRejectedEventConsumer implements Consumer<CommentaireRejectedEvent> {
    private final CommentaireRepository repository;
    private final KafkaWaiterService kafkaWaiterService;

    @Override
    public void accept(CommentaireRejectedEvent evt) {
        log.debug("Commentaired {} rejected!", evt.getCommentaireId());

        this.repository.deleteById(evt.getCommentaireId());

        this.kafkaWaiterService.getWaiter().emitValue(false, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
