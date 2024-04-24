package es.nemes.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
public class Subscription {
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
    @OneToMany(cascade = CascadeType.ALL)
    private List<Event> events;

    public Subscription() { super(); }

    public Subscription(String name, String description, NUser user, Zone zone, List<Event> events) {
        this.name = name;
        this.description = description;
        this.user = user;
        this.zone = zone;
        this.events = events;
    }

    @Transient
    public static final Subscription NOT_FOUND = new Subscription("Unknown", "Unknown", NUser.NOT_FOUND, null, null);

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "NInterestArea{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", zone=" + zone +
                ", events=" + events +
                '}';
    }
}
