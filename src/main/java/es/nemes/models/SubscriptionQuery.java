package es.nemes.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SubscriptionQuery {
    String name;
    String description;
    Zone zone;
    List<Event> events;

    @Override
    public String toString() {
        return "SubscriptionQuery {" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", zone=" + zone +
                ", events=" + events +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Zone getZone() {
        return zone;
    }

    public List<Event> getEvents() {
        return events;
    }
}
