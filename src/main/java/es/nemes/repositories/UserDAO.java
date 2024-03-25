package es.nemes.repositories;

import es.nemes.models.NUser;
import es.nemes.models.NUserLogin;

import java.util.Collection;

public interface UserDAO {
    NUser create(NUser user);
    NUser findByEmail(String email);
    Collection<NUser> getUsers();
}
