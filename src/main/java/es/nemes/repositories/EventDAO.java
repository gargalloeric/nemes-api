package es.nemes.repositories;

import es.nemes.models.Catastrophe;
import es.nemes.models.Event;
import es.nemes.models.NUser;

public interface EventDAO {
    Event findById(String eventName, String severity);
}
