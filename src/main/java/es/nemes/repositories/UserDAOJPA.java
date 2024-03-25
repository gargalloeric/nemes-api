package es.nemes.repositories;

import es.nemes.models.NUser;
import io.quarkus.elytron.security.common.BcryptUtil;
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
        // Before persisting the user, we hash the password.
        user.setPassword(
                BcryptUtil.bcryptHash(user.getPassword())
        );
        em.persist(user);
        return user;
    }

    @Override
    public NUser findByEmail(String email) {
        TypedQuery<NUser> query = em.createNamedQuery("NUser.findByEmail", NUser.class);
        return query.setParameter("email", email).getSingleResult();
    }

    @Override
    public Collection<NUser> getUsers() {
        TypedQuery<NUser> query = em.createNamedQuery("NUser.findAll", NUser.class);
        List<NUser> result = query.getResultList();
        if (result != null) return result;
        return new ArrayList<>();
    }
}
