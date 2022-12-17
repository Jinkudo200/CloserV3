package ma.ac.emi.ginfo.closer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Position {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long latitude;
    private Long longitude;

    @OneToOne
    private Adherent adherent;

    protected Position() {}

    public Position(long latitude, long longitude, Adherent adherent) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.adherent = adherent;
    }

    // Standard Getters and Setters
}