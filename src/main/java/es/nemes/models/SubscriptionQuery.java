package es.nemes.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SubscriptionQuery {
    @Column(precision=6, scale=4)
    BigDecimal centerLat;
    @Column(precision=6, scale=4)
    BigDecimal centerLon;
    List<String> eventsName;
    List<String> eventsSeverity;

    public BigDecimal getCenterLat() {
        return centerLat;
    }

    public BigDecimal getCenterLon() {
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
