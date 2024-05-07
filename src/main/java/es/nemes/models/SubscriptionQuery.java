package es.nemes.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SubscriptionQuery {
    String centerLat;
    String centerLon;
    List<String> eventsName;
    List<String> eventsSeverity;

    public String getCenterLat() {
        return centerLat;
    }

    public String getCenterLon() {
        return centerLon;
    }


    public List<String> getEventsName() {
        return eventsName;
    }

    public void setEventsName(List<String> eventsName) {
        this.eventsName = eventsName;
    }

    public List<String> getEventsSeverity() {
        return eventsSeverity;
    }

    public void setEventsSeverity(List<String> eventsSeverity) {
        this.eventsSeverity = eventsSeverity;
    }

    @Override
    public String toString() {
        return "SubscriptionQuery{" +
                "centerLat=" + centerLat +
                ", centerLon=" + centerLon +
                ", eventsName=" + eventsName +
                ", eventsSeverity=" + eventsSeverity +
                '}';
    }
}
