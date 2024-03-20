package es.nemes.repositories;

import es.nemes.models.NUser;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ApplicationScoped
public class UserDAOJPA implements UserDAO {
    @Inject
    EntityManager em;
    @Override
    @Transactional
    public NUser create(NUser user) {
        em.persist(user);
        return user;
    }

    @Override
    public Collection<NUser> getUsers() {
        TypedQuery<NUser> query = em.createNamedQuery("NUser.findAll", NUser.class);
        List<NUser> result = query.getResultList();
        if (result != null) return result;
        return new ArrayList<>();
    }
}
