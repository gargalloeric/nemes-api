package es.nemes.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NamedQueries({
        @NamedQuery(name = "Subscription.findAll", query = "SELECT s FROM Subscription s")
})
@Entity
public class Subscription {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    NUser user;
    @ManyToOne
    Zone zone;
    @ManyToMany(cascade = CascadeType.ALL)
    List<Event> events;

    public NUser getUser() {
        return user;
    }

    public void setUser(NUser user) {
        this.user = user;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }



    public Subscription() { super(); }

    public Subscription(NUser user, Zone zone, List<Event> events) {
        this.user = user;
        this.zone = zone;
        this.events = events;
    }

    @Transient
    public static final Subscription NOT_FOUND = new Subscription(NUser.NOT_FOUND, null, null);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", user=" + user +
                ", zone=" + zone +
                ", events=" + events +
                '}';
    }
}
