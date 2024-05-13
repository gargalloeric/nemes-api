package es.nemes.repositories;

import es.nemes.models.Catastrophe;

import java.util.List;

public interface CatastropheDAO {
    Catastrophe create(Catastrophe cat);
    List<Catastrophe> getCatastrophes();
    List<Catastrophe> getFilteredCatastrophes();
}
