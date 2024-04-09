package es.nemes.repositories;

import es.nemes.models.Zone;

import java.util.Collection;

public interface ZoneDAO {
    Zone create(Zone zone);
    Collection<Zone> getZones();
}
