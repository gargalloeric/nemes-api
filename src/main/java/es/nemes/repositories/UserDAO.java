package es.nemes.repositories;

import es.nemes.models.NUser;

import java.util.Collection;

public interface UserDAO {
    NUser create(NUser user);
    Collection<NUser> getUsers();
}
