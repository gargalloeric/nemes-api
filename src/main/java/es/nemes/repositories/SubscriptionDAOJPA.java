package es.nemes.repositories;

import es.nemes.models.Subscription;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Collection;

@ApplicationScoped
public class SubscriptionDAOJPA implements SubscriptionDAO {
    @Inject
    EntityManager em;

    @Override
    @Transactional
    public Subscription create(Subscription subscription) {
        em.persist(subscription);
        return subscription;
    }

    @Override
    public Collection<Subscription> getSubscriptions() {
        return null;
    }
}