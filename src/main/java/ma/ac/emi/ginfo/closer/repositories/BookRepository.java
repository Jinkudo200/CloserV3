package ma.ac.emi.ginfo.closer.repositories;

import ma.ac.emi.ginfo.closer.entities.Book;
import ma.ac.emi.ginfo.closer.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookById(Long id);


}
