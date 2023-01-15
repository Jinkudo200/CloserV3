package ma.ac.emi.ginfo.closer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ma.ac.emi.ginfo.closer.enumeration.State;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@IdClass(BookId.class)
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Id
    @ManyToOne(cascade=CascadeType.ALL)
    private Adherent adherent;

    @Id
    @ManyToOne(cascade=CascadeType.ALL)
    private Provider provider;

    @Id
    private LocalDate dateOrdered;

    private State state;

    private LocalDate dateAccepted;

    private LocalDate dateDone;


    public Book() {
    }

    public Book(UUID id, Adherent adherent, Provider provider, LocalDate dateOrdered, State state, LocalDate dateAccepted, LocalDate dateDone) {
        this.id = id;
        this.adherent = adherent;
        this.provider = provider;
        this.dateOrdered = dateOrdered;
        this.state = state;
        this.dateAccepted = dateAccepted;
        this.dateDone = dateDone;
    }

    public Book(Adherent adherent, Provider p, LocalDate dateOrdered) {
        this.adherent = adherent;
        this.provider = p;
        this.dateOrdered = dateOrdered;
        this.state = State.TREATING;
        this.dateAccepted = null;
        this.dateDone = null;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", adherent=" + adherent +
                ", Provider=" + provider +
                ", state=" + state +
                ", dateOrdered=" + dateOrdered +
                ", dateAccepted=" + dateAccepted +
                ", dateDone=" + dateDone +
                '}';
    }
}
