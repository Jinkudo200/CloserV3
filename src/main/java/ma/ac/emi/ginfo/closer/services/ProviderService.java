package ma.ac.emi.ginfo.closer.services;

import ma.ac.emi.ginfo.closer.entities.Adherent;
import ma.ac.emi.ginfo.closer.entities.Provider;
import ma.ac.emi.ginfo.closer.entities.Services;
import ma.ac.emi.ginfo.closer.exceptions.UserNotFoundException;
import ma.ac.emi.ginfo.closer.repositories.AdherentRepository;
import ma.ac.emi.ginfo.closer.repositories.ProviderRepository;
import ma.ac.emi.ginfo.closer.repositories.ProviderRepository;
import ma.ac.emi.ginfo.closer.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {

    ProviderRepository pr;

    AdherentRepository ar;

    public ProviderService(ProviderRepository pr, AdherentRepository ar) {
        this.pr = pr;
        this.ar = ar;
    }

    public List<Provider> providers(){
        return pr.findAll();
    }




    public Provider becomeProvider(Adherent a , Services s){
        Provider provider = new Provider(a.getName(), a.getMail() , s);
        ar.delete(a);
        return pr.save(provider);
    }

    public Provider addProvider(Provider a) {
        return pr.save(a);
    }




    public List<Provider> Providers(){
        return pr.findAll();
    }

    public Provider findProviderById(Long id){
        return pr.findProviderById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public Provider updateProvider(Provider provider){
        return pr.save(provider);
    }

    public void deleteProvider(Long id){
        pr.deleteById(id);
    }
}
