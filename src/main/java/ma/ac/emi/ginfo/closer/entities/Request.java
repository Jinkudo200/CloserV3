package ma.ac.emi.ginfo.closer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.ac.emi.ginfo.closer.enumeration.State;
import ma.ac.emi.ginfo.closer.services.PositionService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Request implements Comparable<Request>{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;

    @ManyToOne
    private Adherent adherent;

    @ManyToOne
    private Services services;

    @ManyToOne(cascade=CascadeType.PERSIST)
    private Position position;

    private String description;

    private State state;


    private LocalDate dateOrdered;


    private LocalDate dateAccepted;

    private LocalDate dateDone;


    @OneToMany(cascade=CascadeType.ALL, mappedBy = "request")
    private List<Offer> offers;

    @OneToOne
    private Offer offerSelected;

    public Request(Adherent adherent, Services services, Position position, String description) {
        this.adherent = adherent;
        this.services = services;
        this.position = position;
        this.description = description;
        this.state = State.TREATING;
        this.offers = new ArrayList<>();
        this.dateOrdered = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", adherent=" + adherent +
                ", services=" + services +
                ", position=" + position +
                ", description='" + description + '\'' +
                ", state=" + state +
                ", dateOrdered=" + dateOrdered +
                ", dateAccepted=" + dateAccepted +
                ", dateDone=" + dateDone +
                ", offers=" + offers +
                ", offerSelected=" + offerSelected +
                '}';
    }

    @Override
    public int compareTo(Request r) {
        PositionService positionService = new PositionService();
        if (positionService.calculateDistanceInMeters(this.getPosition(), PositionService.current.getPosition()) == 0 || positionService.calculateDistanceInMeters(r.getPosition(), PositionService.current.getPosition()) == 0) {
            return 0;
        }
        return positionService.calculateDistanceInMeters(this.getPosition(), PositionService.current.getPosition()).compareTo(positionService.calculateDistanceInMeters(r.getPosition(), PositionService.current.getPosition()));
    }

}
