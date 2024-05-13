package es.nemes.repositories;

import es.nemes.models.Event;
import es.nemes.models.Zone;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;

@ApplicationScoped
public class EventDAOJPA implements EventDAO {
    @Inject
    EntityManager em;

    @Override
    @Transactional
    public Event findById(String eventName, String severity) {
        TypedQuery<Event> query = em.createNamedQuery("Event.findById", Event.class)
                .setParameter("name", eventName)
                .setParameter("severity", severity);
        return query.getSingleResult();
    }
}
