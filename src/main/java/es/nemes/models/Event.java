package es.nemes.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;


@NamedQueries({
        @NamedQuery(name = "findById", query = "SELECT e FROM Event e WHERE e.eventName = :name AND e.severity = :severity")
})

@Entity
public class Event {
    @Id
    private String eventName;
    @Id
    private String severity;
    private String description;

    public Event() {
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + eventName + '\'' +
                ", severity='" + severity + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

