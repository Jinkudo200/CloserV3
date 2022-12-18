package ma.ac.emi.ginfo.closer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ma.ac.emi.ginfo.closer.services.PositionService;

@Entity
@Getter
@Setter
public class Position implements Comparable<Position> {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long latitude;
    private Long longitude;


    public Position(Long latitude, Long longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }


    @OneToOne
    private Adherent adherent;

    protected Position() {}

    public Position(long latitude, long longitude, Adherent adherent) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.adherent = adherent;
    }

    public Position(Long id, Long latitude, Long longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    //    @Override
    public int compareTo(Position p) {
        PositionService positionService = new PositionService();
        if (positionService.calculateDistanceInMeters(this, PositionService.current) == 0 || positionService.calculateDistanceInMeters(p, PositionService.current) == 0) {
            return 0;
        }
        return positionService.calculateDistanceInMeters(this, PositionService.current).compareTo(positionService.calculateDistanceInMeters(p, PositionService.current));
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
// Standard Getters and Setters
}