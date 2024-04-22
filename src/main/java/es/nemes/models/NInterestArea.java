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

    public NInterestArea(String name, String description, NUser user, Zone zone) {
        this.name = name;
        this.description = description;
        this.user = user;
        this.zone = zone;
    }

    @Transient
    public static final NInterestArea NOT_FOUND = new NInterestArea("Unknown", "Unknown", NUser.NOT_FOUND, null);


    @Override
    public String toString() {
        return "NInterestArea{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", zone=" + zone +
                '}';
    }

    public Long getId() {
        return id;
    }
}
