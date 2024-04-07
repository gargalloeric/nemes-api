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

    public NInterestArea() { super(); }

    public NInterestArea(String name, String description, NUser user) {
        this.name = name;
        this.description = description;
        this.user = user;
    }

}
