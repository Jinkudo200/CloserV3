package ma.ac.emi.ginfo.closer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ma.ac.emi.ginfo.closer.enumeration.State;
import ma.ac.emi.ginfo.closer.services.PositionService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("A")
public class Adherent implements Comparable<Adherent>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;

    @OneToOne(cascade=CascadeType.PERSIST)
    private Position position;

    @OneToOne(cascade=CascadeType.PERSIST)
    private Compte compte;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Book> services = new ArrayList<>();

    private boolean isProvider;

    @ManyToMany
    private List<Provider> favoris = new ArrayList<>();



    public Adherent() {
    }

    public Adherent(String name , Position position) {
        this.name = name;
        this.position = position;
    }

    public Adherent(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Adherent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", isProvider=" + isProvider +
                '}';
    }

    @Override
    public int compareTo(Adherent a) {
        PositionService positionService = new PositionService();
        if (positionService.calculateDistanceInMeters(this.getPosition(), PositionService.current.getPosition()) == 0 || positionService.calculateDistanceInMeters(a.getPosition(), PositionService.current.getPosition()) == 0) {
            return 0;
        }
        return positionService.calculateDistanceInMeters(this.getPosition(), PositionService.current.getPosition()).compareTo(positionService.calculateDistanceInMeters(a.getPosition(), PositionService.current.getPosition()));
    }


}
