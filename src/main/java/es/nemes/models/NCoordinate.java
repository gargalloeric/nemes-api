package es.nemes.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class NCoordinate {
    float latitude;
    float longitude;

    public NCoordinate() { super(); }

    public NCoordinate(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
