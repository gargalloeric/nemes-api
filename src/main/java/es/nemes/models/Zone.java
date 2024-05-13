package es.nemes.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@NamedQueries({
        @NamedQuery(name = "Zone.findAll", query = "SELECT z FROM Zone z"),
        @NamedQuery(name = "Zone.findByCenter", query = "SELECT z FROM Zone z WHERE z.centerLat = :lat AND z.centerLon = :lon")
})
public class Zone {

    // mannually added center, don't use coordinate as it will difficult queries
    @Id
    @Column(precision=6, scale=4)
    private BigDecimal centerLat;

    @Id
    @Column(precision=6, scale=4)
    private BigDecimal centerLon;

    private int radius;
    private String descriptiveName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zone")
    private List<Coordinate> polygon;

    public Zone() {
    }

    public int getRadius() {
        return radius;
    }

    public List<Coordinate> getPolygons() {
        return polygon;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setPolygon(List<Coordinate> polygon) {
        this.polygon = polygon;
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
                ", polygon=" + polygon +
                '}';
    }

    public void configureCoordinates() {
        for (Coordinate coordinate : polygon) {
            coordinate.setZone(this);
        }
    }

    public String getDescriptiveName() {
        return descriptiveName;
    }

    public void setDescriptiveName(String descriptiveName) {
        this.descriptiveName = descriptiveName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Zone zone)) return false;
        return Objects.equals(getCenterLat(), zone.getCenterLat()) && Objects.equals(getCenterLon(), zone.getCenterLon());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCenterLat(), getCenterLon());
    }
}
