package ma.ac.emi.ginfo.closer.services;

import ma.ac.emi.ginfo.closer.entities.Adherent;
import ma.ac.emi.ginfo.closer.entities.Book;
import ma.ac.emi.ginfo.closer.entities.Provider;
import ma.ac.emi.ginfo.closer.enumeration.State;
import ma.ac.emi.ginfo.closer.exceptions.UserNotFoundException;
import ma.ac.emi.ginfo.closer.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

    public Book acceptBook(Book book) {
        book.setDateAccepted(LocalDate.now());
        book.setState(State.ACCEPTED);
        return br.save(book);
    }

    public Book finishBook(Book book) {
        book.setDateDone(LocalDate.now());
        book.setState(State.DONE);
        return br.save(book);
    }

    public Book refuseBook(Book book) {
        book.setState(State.REFUSED);
        return br.save(book);
    }


    public List<Book> books(){
        return br.findAll();
    }

    public Book findBookById(UUID id){
        return br.findBookById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + "was not found"));
    }

    public Book updateBook(Book book){
        return br.save(book);
    }

    public void deleteBook(UUID id){
        br.deleteById(id);
    }
}
