package es.nemes.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.transaction.Transactional;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@NamedQueries({
        @NamedQuery(name = "Event.findById", query = "SELECT e FROM Event e WHERE e.eventName = :name AND e.severity = :severity")
})
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event event)) return false;
        return Objects.equals(getEventName(), event.getEventName()) && Objects.equals(getSeverity(), event.getSeverity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEventName(), getSeverity());
    }
}

