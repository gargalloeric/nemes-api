package es.nemes.models;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
public class Zone {
    @Id
    @GeneratedValue
    private Long id;
    @JdbcTypeCode(SqlTypes.JSON)
    private Coordinate center;
    private int radius;
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Coordinate> polygons;

    public Zone() {
    }

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

    public List<Coordinate> getPolygon() {
        return polygons;
    }

    public void setCenter(Coordinate center) {
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
