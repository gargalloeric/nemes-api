package es.nemes.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SubscriptionQuery {
    String name;
    String description;
    @Column(precision=6, scale=4)
    BigDecimal centerLat;
    @Column(precision=6, scale=4)
    BigDecimal centerLon;
    List<Event> events;


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getCenterLat() {
        return centerLat;
    }

    public BigDecimal getCenterLon() {
        return centerLon;
    }


    public List<Event> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return "SubscriptionQuery{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", centerLat=" + centerLat +
                ", centerLon=" + centerLon +
                ", events=" + events +
                '}';
    }
}
