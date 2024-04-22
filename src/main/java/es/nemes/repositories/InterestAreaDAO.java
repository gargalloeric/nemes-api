package es.nemes.repositories;

import es.nemes.models.NInterestArea;

import java.util.Collection;

public interface InterestAreaDAO {
    NInterestArea create(NInterestArea area);
    Collection<NInterestArea> getInterestsArea();
}
