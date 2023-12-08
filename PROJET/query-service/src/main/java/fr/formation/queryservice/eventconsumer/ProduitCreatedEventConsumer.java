package fr.formation.queryservice.eventconsumer;

import java.util.function.Consumer;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import fr.formation.queryservice.event.ProduitCreatedEvent;
import fr.formation.queryservice.model.Produit;
import fr.formation.queryservice.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component("onProduitCreated")
@RequiredArgsConstructor
@Log4j2
public class ProduitCreatedEventConsumer implements Consumer<ProduitCreatedEvent> {
    private final ProduitRepository repository;

    @Override
    public void accept(ProduitCreatedEvent evt) {
        log.debug("Product {} created ...", evt.getId());

        Produit produit = new Produit();

        BeanUtils.copyProperties(evt, produit);
        produit.setNote(-1);

        this.repository.save(produit);
    }
}
