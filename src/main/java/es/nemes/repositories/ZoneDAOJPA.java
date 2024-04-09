package es.nemes.repositories;

import es.nemes.models.Zone;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;

import java.util.Collection;

@ApplicationScoped
public class ZoneDAOJPA implements ZoneDAO {
    @Inject
    EntityManager em;

    @Override
    @Transactional
    public Zone create(Zone zone) {
        em.persist(zone);
        return zone;
    }

    @Override
    public Collection<Zone> getZones() {
        return null;
    }
}
