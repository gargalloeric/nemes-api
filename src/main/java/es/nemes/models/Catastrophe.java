package es.nemes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;


@NamedQueries({
        @NamedQuery(name = "findActiveById", query = "SELECT c FROM Catastrophe c " +
                "WHERE c.name = :name " +
                "AND c.lastValidDate >= CURRENT_DATE " +
                "AND c.startDate = :startdate " +
                "AND c.event.eventName = :eventname " +
                "AND c.event.severity = :severity " +
                "AND 0.1 > abs(:centerlat - c.zone.centerLat) " +
                "AND 0.1 > abs(:centerlon - c.zone.centerLon) "
        ),
        @NamedQuery(name = "Catastrophe.findAll", query = "SELECT c FROM Catastrophe c "
        ),
        @NamedQuery(name = "Catastrophe.findFiltered", query = "SELECT c FROM Catastrophe c " +
                "WHERE c.startDate >= :startdate " +
                "AND c.lastValidDate <= :finishdate "
        ),
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "zonecenterlat", referencedColumnName = "centerlat"),
            @JoinColumn(name = "zonecenterlon", referencedColumnName = "centerlon")})

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
