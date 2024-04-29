package es.nemes.repositories;

import es.nemes.models.NUser;
import es.nemes.models.Zone;

import java.math.BigDecimal;
import java.util.Collection;

public interface ZoneDAO {
    Zone create(Zone zone);
    Zone findByCenter(BigDecimal centerLat, BigDecimal centerLon);

    Collection<Zone> getZones();
}