package ma.ac.emi.ginfo.closer.controllors;

import ma.ac.emi.ginfo.closer.entities.Adherent;
import ma.ac.emi.ginfo.closer.entities.Book;
import ma.ac.emi.ginfo.closer.entities.Provider;
import ma.ac.emi.ginfo.closer.services.AdherentService;
import ma.ac.emi.ginfo.closer.services.BookService;
import ma.ac.emi.ginfo.closer.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/books")
@RestController
public class BookControllor {

    @Autowired
    BookService bs;

    @Autowired
    AdherentService as;

    @Autowired
    ProviderService ps;

    @GetMapping("/")
    public ResponseEntity<List<Book>> books(){
        List<Book> books = bs.books();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


//    @GetMapping("/find/{id}")
//    public Book book(@PathVariable(name = "id") Long id){
//        return bs.findBookById(id);
//    }

    @PostMapping("/add/{idAdherent}/{idProvider}")
    public ResponseEntity<Book> addBook(@PathVariable("idAdherent") Long idAdherent,
                                        @PathVariable("idProvider") Long idProvider) {
        Adherent adherent = as.findAdherentById(idAdherent);
        Provider provider = ps.findProviderById(idProvider);
        Book newBook = bs.addBook(adherent , provider);
//        adherent.getProvider().add(newBook);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

//    @GetMapping("/find/{id}")
//    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
//        Book books = bs.findBookById(id);
//        return new ResponseEntity<>(books, HttpStatus.OK);
//    }

    @PutMapping("/update")
    public ResponseEntity<Book> updateBook(@RequestBody Book adherent) {
        Book updateBook = bs.updateBook(adherent);
        return new ResponseEntity<>(updateBook, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        bs.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
