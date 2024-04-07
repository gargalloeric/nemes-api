package es.nemes.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
public class NArea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Embedded
    @Column(unique = true)
    NCoordinate center_coordinates;
    @Column(unique = true)
    int radio;
    @OneToOne
    NInterestArea area;


    public NArea() { super(); }

    public NArea(NCoordinate center_coordinates, int radio) {
        this.center_coordinates = center_coordinates;
        this.radio = radio;
    }
}
