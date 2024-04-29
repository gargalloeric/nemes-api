package es.nemes.repositories;

import es.nemes.models.NUser;
import es.nemes.models.Zone;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Transient;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    public Zone findByCenter(BigDecimal centerLat, BigDecimal centerLon) {
        TypedQuery<Zone> query = em.createNamedQuery("Zone.findByCenter", Zone.class)
                .setParameter("lat", centerLat)
                .setParameter("lat", centerLon);
        return query.getSingleResult();
    }

    @Override
    public Collection<Zone> getZones() {
        TypedQuery<Zone> query = em.createNamedQuery("Zone.findAll", Zone.class);
        List<Zone> result = query.getResultList();
        if (result != null) return result;
        return new ArrayList<>();
    }
}