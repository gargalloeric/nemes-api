package es.nemes.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
public class NInterestArea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    String name;
    String description;
    @ManyToOne
    NUser user;
    @OneToOne
    Zone zone;

    public NInterestArea() { super(); }

    public NInterestArea(String name, String description) {
        this.name = name;
        this.description = description;
        this.zone = zone;
    }

    public void setUser(NUser user) {
        this.user = user;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
