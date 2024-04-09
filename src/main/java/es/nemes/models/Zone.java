package es.nemes.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Zone {

    @Id
    @GeneratedValue
    private Long id;

    private int center;
    private int radius;
    @OneToMany
    private List<Coordinate> polygons;

    public Zone() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public List<Coordinate> getPolygon() {
        return polygons;
    }

    public void setCenter(int center) {
        this.center = center;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setPolygon(List<Coordinate> polygon) {
        this.polygons = polygon;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "id=" + id +
                ", center=" + center +
                ", radius=" + radius +
                ", polygons=" + polygons +
                '}';
    }
}
