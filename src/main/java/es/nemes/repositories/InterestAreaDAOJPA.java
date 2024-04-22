package es.nemes.repositories;

import es.nemes.models.NInterestArea;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Collection;

@ApplicationScoped
public class InterestAreaDAOJPA implements InterestAreaDAO {
    @Inject
    EntityManager em;

    @Override
    @Transactional
    public NInterestArea create(NInterestArea area) {
        em.persist(area);
        return area;
    }

    @Override
    public Collection<NInterestArea> getInterestsArea() {
        return null;
    }
}
