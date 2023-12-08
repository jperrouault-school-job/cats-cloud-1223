package fr.formation.commentaireservice.eventconsumer;

import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import fr.formation.commentaireservice.command.CreateCommentaireAcceptedCommand;
import fr.formation.commentaireservice.event.CommentaireAcceptedEvent;
import fr.formation.commentaireservice.model.Commentaire;
import fr.formation.commentaireservice.model.Commentaire.State;
import fr.formation.commentaireservice.repository.CommentaireRepository;
import fr.formation.commentaireservice.service.KafkaWaiterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Sinks;

@Component("onCommentaireAccepted")
@RequiredArgsConstructor
@Log4j2
public class CommentaireAcceptedEventConsumer implements Consumer<CommentaireAcceptedEvent> {
    private final CommentaireRepository repository;
    private final KafkaWaiterService kafkaWaiterService;
    private final StreamBridge streamBridge;

    @Override
    public void accept(CommentaireAcceptedEvent evt) {
        log.debug("Commentaired {} accepted!", evt.getCommentaireId());

        Optional<Commentaire> optCommentaire = this.repository.findById(evt.getCommentaireId());

        if (optCommentaire.isEmpty()) {
            return;
        }

        Commentaire commentaire = optCommentaire.get();

        commentaire.setState(State.OK);
        this.repository.save(commentaire);

        this.streamBridge.send("cqrs.commentaire.created", CreateCommentaireAcceptedCommand.builder()
            .id(commentaire.getId())
            .text(commentaire.getText())
            .noteQualite(commentaire.getNoteQualite())
            .noteQualitePrix(commentaire.getNoteQualitePrix())
            .noteFacilite(commentaire.getNoteFacilite())
            .produitId(commentaire.getProduitId())
            .build()
        );

        this.kafkaWaiterService.getWaiter().emitValue(true, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
