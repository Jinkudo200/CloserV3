package ma.ac.emi.ginfo.closer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ma.ac.emi.ginfo.closer.services.PositionService;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class Position implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long latitude;
    private Long longitude;

    @JsonIgnore
    @OneToOne(mappedBy = "position", cascade=CascadeType.ALL)
    private Adherent adherent;


    public Position(Long latitude, Long longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }



//    @JsonIgnore
//    @OneToOne(mappedBy = "positionP", cascade=CascadeType.ALL)
//    private Provider provider;


    protected Position() {}

//    public Position(long latitude, long longitude) {
//        this.latitude = latitude;
//        this.longitude = longitude;
//    }

    public Position(Long latitude, Long longitude, Adherent adherent) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.adherent = adherent;
    }

//    public Position(Long latitude, Long longitude, Provider provider) {
//        this.latitude = latitude;
//        this.longitude = longitude;
//        this.provider = provider;
//    }

    //    @Override

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", adherent=" + adherent.getName() +
                '}';
    }


// Standard Getters and Setters
}