package ma.ac.emi.ginfo.closer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Provider provider;

    @JsonIgnore
    @OneToOne
    private Adherent adherent;

    private float rate;

    private String description;

    public Rating() {
    }

    public Rating(float rate, String description) {
        this.rate = rate;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return rate == rating.rate && Objects.equals(id, rating.id) && Objects.equals(description, rating.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rate, description);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", provider=" + provider.getId() +
                ", adherent=" + adherent.getId() +
                ", rate=" + rate +
                ", description='" + description + '\'' +
                '}';
    }
}
