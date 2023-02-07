package ma.ac.emi.ginfo.closer.services;

import ma.ac.emi.ginfo.closer.entities.*;
import ma.ac.emi.ginfo.closer.exceptions.UserNotFoundException;
import ma.ac.emi.ginfo.closer.repositories.*;
import ma.ac.emi.ginfo.closer.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProviderService {

    ProviderRepository pr;

    AdherentRepository ar;

    RequestRepository rr;

    public ProviderService(ProviderRepository pr, AdherentRepository ar, RequestRepository rr) {
        this.pr = pr;
        this.ar = ar;
        this.rr = rr;
    }

    public List<Provider> providers() {
        return pr.findAll();
    }


    public Provider becomeProvider(Adherent a, Services s) {

        Provider provider = new Provider(a, s);

        ar.delete(a);
        return pr.save(provider);
    }

    public Provider addProvider(Provider a) {
        return pr.save(a);
    }


    public List<Provider> Providers() {
        return pr.findAll();
    }

    public Provider findProviderById(Long id) {
        return pr.findProviderById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public Provider findProviderByIdP(Long id) {
        return pr.findProviderByIdP(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public Provider updateProvider(Provider provider) {
        return pr.save(provider);
    }

    public void deleteProvider(Long id) {
        pr.deleteById(id);
    }
}
