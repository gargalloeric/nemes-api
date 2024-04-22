package es.nemes.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Zone {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Coordinate center;
    private int radius;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Coordinate> polygons;

    public Zone() {
    }

    public Zone(Coordinate center, int radius, List<Coordinate> polygons) {
        this.center = center;
        this.radius = radius;
        this.polygons = polygons;
    }

    @Transient
    public static final Zone NOT_FOUND = new Zone(null, 0, null);


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Coordinate getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public List<Coordinate> getPolygons() {
        return polygons;
    }

    public void setCenter(Coordinate center) {
        this.center = center;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setPolygons(List<Coordinate> polygon) {
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
