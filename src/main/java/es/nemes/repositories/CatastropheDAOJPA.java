package es.nemes.repositories;

import es.nemes.models.Catastrophe;
import es.nemes.models.NUser;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class CatastropheDAOJPA implements CatastropheDAO {
    @Inject
    EntityManager em;
    @Override
    @Transactional
    public Catastrophe create(Catastrophe cat) {
        em.persist(cat);
        return cat;
    }

    @Override
    public List<Catastrophe> getCatastrophes() {
        TypedQuery<Catastrophe> query = em.createNamedQuery("Catastrophe.findAll", Catastrophe.class);
        List<Catastrophe> result = query.getResultList();
        if (result != null) return result;
        return new ArrayList<>();
    }

    @Override
    public List<Catastrophe> getFilteredCatastrophes(LocalDate startDate, LocalDate finishDate, List<String> events) {
        TypedQuery<Catastrophe> query = em.createNamedQuery("Catastrophe.findFiltered", Catastrophe.class)
                .setParameter("startdate", startDate)
                .setParameter("finishdate", finishDate)
                .setParameter("eventnames", events);
        List<Catastrophe> result = query.getResultList();
        if (result != null) return result;
        return new ArrayList<>();
    }
}
