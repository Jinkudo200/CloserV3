package ma.ac.emi.ginfo.closer.repositories;

import ma.ac.emi.ginfo.closer.entities.Adherent;
import ma.ac.emi.ginfo.closer.entities.Book;
import ma.ac.emi.ginfo.closer.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookById(UUID id);

    List<Book> findBooksByAdherent(Adherent a);

    void deleteById(UUID id);
}
