package es.nemes.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@NamedQueries({
        @NamedQuery(name = "Zone.findAll", query = "SELECT z FROM Zone z"),
        @NamedQuery(name = "Zone.findByCenter", query = "SELECT z FROM Zone z WHERE z.centerLat = :lat AND z.centerLon = :lon")
})
public class Zone {

    @Id
    @Column(precision=6, scale=4)
    private BigDecimal centerLat;

    @Id
    @Column(precision=6, scale=4)
    private BigDecimal centerLon;

    private int radius;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zone")
    private List<Coordinate> polygons;

    public Zone() {
    }

    public Zone(BigDecimal centerLat, BigDecimal centerLon, int radius, List<Coordinate> polygons) {
        this.centerLat = centerLat;
        this.centerLon = centerLon;
        this.radius = radius;
        this.polygons = polygons;
    }

    @Transient
    public static final Zone NOT_FOUND = new Zone(new BigDecimal(0), new BigDecimal(0), 0, null);

    public List<Coordinate> getPolygons() {
        return polygons;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setPolygons(List<Coordinate> polygon) {
        this.polygons = polygon;
    }

    public BigDecimal getCenterLat() {
        return centerLat;
    }

    public void setCenterLat(BigDecimal lat) {
        this.centerLat = lat;
    }

    public BigDecimal getCenterLon() {
        return centerLon;
    }

    public void setCenterLon(BigDecimal lon) {
        this.centerLon = lon;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "lat=" + centerLat +
                ", lon=" + centerLon +
                ", radius=" + radius +
                ", polygons=" + polygons +
                '}';
    }
}
