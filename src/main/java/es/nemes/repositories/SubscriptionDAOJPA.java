package es.nemes.repositories;

import es.nemes.models.Catastrophe;
import es.nemes.models.Event;
import es.nemes.models.Subscription;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    public List<Subscription> getSubscriptions() {
        TypedQuery<Subscription> query = em.createNamedQuery("Subscription.findAll", Subscription.class);
        List<Subscription> result = query.getResultList();
        if (result != null) return result;
        return new ArrayList<>();
    }
}
