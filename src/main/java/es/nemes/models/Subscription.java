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
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "subscribed_events", joinColumns = @JoinColumn(name = "subscriptrion_id"))
    @Column(name = "event", nullable = false)
    List<String> subscribedEventsNames;

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

    public List<String> getEvents() {
        return subscribedEventsNames;
    }

    public void setEvents(List<String> events) {
        this.subscribedEventsNames = events;
    }


    public Subscription() { super(); }

    public Subscription(NUser user, Zone zone, List<String> eventsNames) {
        this.user = user;
        this.zone = zone;
        this.subscribedEventsNames = eventsNames;
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
                ", events=" + subscribedEventsNames +
                '}';
    }
}
