package ma.ac.emi.ginfo.closer.services;

import ma.ac.emi.ginfo.closer.entities.Adherent;
import ma.ac.emi.ginfo.closer.entities.Position;
import ma.ac.emi.ginfo.closer.entities.Provider;
import ma.ac.emi.ginfo.closer.entities.Services;
import ma.ac.emi.ginfo.closer.exceptions.UserNotFoundException;
import ma.ac.emi.ginfo.closer.repositories.AdherentRepository;
import ma.ac.emi.ginfo.closer.repositories.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdherentService {



    AdherentRepository ar;
    ServiceRepository sr;

    public AdherentService(AdherentRepository ar, ServiceRepository sr) {
        this.ar = ar;
        this.sr = sr;
    }


    public Adherent addAdherent( Adherent a) {
        return ar.save(a);
    }

    public Adherent addAdherent(Adherent a , Position p) {
        a.setPosition(p);
        return ar.save(a);
    }


    public List<Adherent> adherents(){
        return ar.findAll();
    }

    public Adherent findAdherentById(Long id){
        return ar.findAdherentById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public Adherent updateAdherent(Adherent adherent){
        return ar.save(adherent);
    }

    public void deleteAdherent(Long id){
        ar.deleteById(id);
    }

    public Adherent addServiceToFavoris(Provider a , Adherent adherent) {
        adherent.getFavoris().add(a);
        return ar.save(adherent);
    }

}
