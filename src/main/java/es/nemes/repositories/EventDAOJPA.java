package es.nemes.repositories;

import es.nemes.models.Event;
import es.nemes.models.Zone;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.math.BigDecimal;

@ApplicationScoped
public class EventDAOJPA implements EventDAO {
    @Inject
    EntityManager em;

    @Override
    public Event findById(String eventName, String severity) {
        TypedQuery<Event> query = em.createNamedQuery("Event.findById", Event.class)
                .setParameter("eventName", eventName)
                .setParameter("severity", severity);
        return query.getSingleResult();
    }
}
