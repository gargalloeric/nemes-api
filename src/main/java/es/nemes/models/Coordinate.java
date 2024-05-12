package es.nemes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Coordinate {

    @Id
    @GeneratedValue
    private Long id;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "zonecenterlat", referencedColumnName = "centerLat"),
            @JoinColumn(name = "zonecenterlon", referencedColumnName = "centerLon"),
    })
    @JsonIgnore
    private Zone zone;

    private float lat;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
