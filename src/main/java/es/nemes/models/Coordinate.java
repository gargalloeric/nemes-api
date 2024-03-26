package es.nemes.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Coordinate {
    @Id
    private float lat;
    @Id
    private float lon;

    public Coordinate() {
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }
}
