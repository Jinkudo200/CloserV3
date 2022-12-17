package ma.ac.emi.ginfo.closer.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class BookId implements Serializable {

    private UUID id;
    private Adherent adherent;
    private Provider provider;
    private LocalDate dateOrdered;


    public BookId() {
    }

    public BookId(Adherent adherent, Provider provider, LocalDate dateOrdered) {
        this.adherent = adherent;
        this.provider = provider;
        this.dateOrdered = dateOrdered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookId bookId = (BookId) o;
        return Objects.equals(adherent, bookId.adherent) && Objects.equals(provider, bookId.provider) && Objects.equals(dateOrdered, bookId.dateOrdered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adherent, provider, dateOrdered);
    }
}