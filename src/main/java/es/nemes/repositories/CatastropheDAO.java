package es.nemes.repositories;

import es.nemes.models.Catastrophe;
import es.nemes.models.NUser;
import es.nemes.models.NUserLogin;

import java.util.Collection;

public interface CatastropheDAO {
    Catastrophe create(Catastrophe cat);
    Collection<Catastrophe> getCatastrophes();
}
