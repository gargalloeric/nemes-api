package es.nemes.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NamedQueries({
        @NamedQuery(name = "Catastrophe.findAll", query = "SELECT u FROM NUser u")
})
public class Catastrophe {
    @Id
    private String identifier;
    private String severity;
    @Id
    private String completeName;
    private String description;
    private LocalDate startDate;
    private LocalDate lastValidDate;
    @OneToMany
    private List<Zone> zone;


    public Catastrophe() {}


    public String getIdentifier() {
        return identifier;
    }

    public String getSeverity() {
        return severity;
    }

    public String getCompleteName() {
        return completeName;
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



    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
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


    public List<Zone> getZone() {
        return zone;
    }

    public void setZone(List<Zone> zone) {
        this.zone = zone;
    }
}
