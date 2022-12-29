package ma.ac.emi.ginfo.closer;

import ma.ac.emi.ginfo.closer.entities.*;
import ma.ac.emi.ginfo.closer.repositories.*;
import ma.ac.emi.ginfo.closer.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.commons.beanutils.BeanUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class CloserApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CloserApplication.class, args);
    }


    @Autowired
    ServicesService servicesService;


    @Autowired
    AdherentService adherentService;

    @Autowired
    ProviderService providerService;

    @Autowired
    RatingService ratingService;

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {

//        Services menuisier = new Menuisier();
//        System.out.println(menuisier);
//        servicesService.addServices(menuisier);
//        Menage menage = new Menage();
//        Adherent adherent = new Adherent("spongebob", "spongebob@gmail.com");
//        adherentService.addAdherent(adherent);
//        Provider provider1 = new Provider("Ayman", "ayman@gmail.com", menuisier);
////        Provider provider2 = new Provider("imane" , menage);
//        providerService.addProvider(provider1);
//        Adherent adherent2 = new Adherent("sofi", "spongebob@gmail.com");
//        adherentService.addServiceToFavoris(provider1, adherent2);
//        servicesService.addServices(menage);
//        providerService.becomeProvider(adherent, menage);
//
//
//        Rating rating = new Rating(3, "WTF");
//
//        ratingService.addRating(rating, adherent2, provider1);
//
//        Rating rating2 = new Rating(2, "WTH");
//
//        ratingService.addRating(rating2, adherent2, provider1);
//
//
////        Book book = new Book(adherent2 , provider1 , LocalDate.now());
////        System.out.println(book);
////        bookRepository.save(book);
//        bookService.addBook(adherent2, provider1);
//

//        List<Adherent> positions = new ArrayList<>();
//
//        Position p1 = new Position(1L,10L , 13L);
//        Position p3 = new Position(2L,30L , 27L);
//        Position p2 = new Position(3L,20L , 19L);
//
//        Adherent a1 = new Adherent("mouad", "mofo" , p1);
//        Adherent a2 = new Adherent("samih", "samiha" , p2);
//        Adherent a3 = new Adherent("gogo", "gogo" , p3);
//
//        positions.add(a1);
//        positions.add(a2);
//        positions.add(a3);
//
//        PositionService positionService = new PositionService();
//        PositionService.current = new Adherent("Ayman" , "song", new Position(50L, 9090L));
//
//        List<Double> distances = new ArrayList<>();
//
//        for (Adherent p: positions) {
//            distances.add(positionService.calculateDistanceInMeters(PositionService.current.getPosition(), p.getPosition()));
//        }
//
//        System.out.println(positions);
//        System.out.println(distances);
//        Collections.sort(positions);
//
//        System.out.println(positions);


//        Adherent adherent = new Adherent("ayman");
//
//        System.out.println(adherent);
//
//        Provider p = new Provider();
//
//        BeanUtils.copyProperties(p, adherent);
//
//        System.out.println(p);
//        System.out.println(adherent);


    }
}
