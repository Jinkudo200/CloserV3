package ma.ac.emi.ginfo.closer.services;

import ma.ac.emi.ginfo.closer.entities.Adherent;
import ma.ac.emi.ginfo.closer.entities.Book;
import ma.ac.emi.ginfo.closer.entities.Provider;
import ma.ac.emi.ginfo.closer.entities.Services;
import ma.ac.emi.ginfo.closer.exceptions.UserNotFoundException;
import ma.ac.emi.ginfo.closer.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {

    BookRepository br;

    public BookService(BookRepository br) {
        this.br = br;
    }

    public Book addBook(Adherent a , Provider p) {
        Book book = new Book(a,p, LocalDate.now());
        return br.save(book);
    }

    public List<Book> books(){
        return br.findAll();
    }

    public Book findBookById(Long id){
        return br.findBookById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + "was not found"));
    }

    public Book updateBook(Book book){
        return br.save(book);
    }

    public void deleteBook(Long id){
        br.deleteById(id);
    }
}
