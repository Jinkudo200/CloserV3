package ma.ac.emi.ginfo.closer.controllors;

import ma.ac.emi.ginfo.closer.entities.Adherent;
import ma.ac.emi.ginfo.closer.entities.Provider;
import ma.ac.emi.ginfo.closer.entities.Rating;
import ma.ac.emi.ginfo.closer.entities.Services;
import ma.ac.emi.ginfo.closer.services.AdherentService;
import ma.ac.emi.ginfo.closer.services.ProviderService;
import ma.ac.emi.ginfo.closer.services.RatingService;
import ma.ac.emi.ginfo.closer.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RequestMapping("/rating")
@RestController
public class RatingControllor {

    @Autowired
    RatingService rs;

    @Autowired
    ServicesService ss;

    @Autowired
    ProviderService ps;

    @Autowired
    AdherentService as;


    @GetMapping("/")
    public List<Rating> rating(){
        return rs.ratings();
    }



    @PostMapping("/add/{idAdherent}/{idService}")
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating ,
                                            @PathVariable("idAdherent") Long idAdherent,
                                            @PathVariable("idService") Long idProvider) {
        Adherent adherent = as.findAdherentById(idAdherent);
        Provider provider = ps.findProviderById(idProvider);
        Rating newRating = rs.addRating(rating , adherent, provider);
        return new ResponseEntity<>(newRating, HttpStatus.CREATED);
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable("id") Long id) {
        Rating rating = rs.findRatingById(id);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Rating> updateRating(@RequestBody Rating rating) {
        Rating updateRating = rs.updateRating(rating);
        return new ResponseEntity<>(updateRating, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRating(@PathVariable("id") Long id) {
        rs.deleteRating(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
