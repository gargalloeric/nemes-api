package es.nemes.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;

import java.time.LocalDate;


//@IdClass(CatastropheKey.class)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NamedQueries({
        @NamedQuery(name = "Catastrophe.findAll", query = "SELECT c FROM Catastrophe c")
})

@Entity
public class Catastrophe {
    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumns({
            @JoinColumn(name = "eventname", referencedColumnName = "eventname"),
            @JoinColumn(name = "serverity", referencedColumnName = "severity")})
    private Event event;
    @Id
    private String name;
    private String description;
    @Id
    private LocalDate startDate;
    private LocalDate lastValidDate;
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    private Zone zone;

    public Catastrophe() {}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getLastValidDate() {
        return lastValidDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setLastValidDate(LocalDate lastValidDate) {
        this.lastValidDate = lastValidDate;
    }


    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Catastrophe{" +
                "event=" + event +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", lastValidDate=" + lastValidDate +
                ", zone=" + zone +
                '}';
    }
}