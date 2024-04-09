package es.nemes.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class InterestAreaQuery {
    String name;
    String description;
    Zone zone;

    @Override
    public String toString() {
        return "InterestAreaQuery{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", zone='" + zone + '\'' +
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
}
