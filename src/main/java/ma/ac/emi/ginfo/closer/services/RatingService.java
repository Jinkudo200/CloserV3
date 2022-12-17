package ma.ac.emi.ginfo.closer.services;

import ma.ac.emi.ginfo.closer.entities.Adherent;
import ma.ac.emi.ginfo.closer.entities.Provider;
import ma.ac.emi.ginfo.closer.entities.Rating;
import ma.ac.emi.ginfo.closer.entities.Services;
import ma.ac.emi.ginfo.closer.exceptions.UserNotFoundException;
import ma.ac.emi.ginfo.closer.repositories.ProviderRepository;
import ma.ac.emi.ginfo.closer.repositories.RatingRepository;
import ma.ac.emi.ginfo.closer.repositories.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    RatingRepository rr;

    ProviderRepository pr;

    public RatingService(RatingRepository rr, ProviderRepository pr) {
        this.rr = rr;
        this.pr = pr;
    }

    public Rating addRating(Rating a , Adherent adherent , Provider provider) {
        a.setProvider(provider);
        a.setAdherent(adherent);
        provider.getRatings().add(a);
        provider.setRate(calculMoyenne(provider));
        pr.save(provider);
        return rr.save(a);
    }

    public Rating addRating(Rating a) {
        return rr.save(a);
    }


    public List<Rating> ratings(){
        return rr.findAll();
    }

    public Rating findRatingById(Long id){
        return rr.findRatingById(id)
                .orElseThrow(() -> new UserNotFoundException("Rating by id " + id + "was not found"));
    }

    public Rating updateRating(Rating rating){
        return rr.save(rating);
    }

    public void deleteRating(Long id){
        rr.deleteById(id);
    }

    public float calculMoyenne(Provider provider) {
        float somme = 0;
        for (int i = 0; i < provider.getRatings().size(); i++)
            somme += provider.getRatings().get(i).getRate();
        return somme / provider.getRatings().size();
    }

}
