package ma.ac.emi.ginfo.closer.services;

import ma.ac.emi.ginfo.closer.entities.Services;
import ma.ac.emi.ginfo.closer.exceptions.UserNotFoundException;
import ma.ac.emi.ginfo.closer.repositories.AdherentRepository;
import ma.ac.emi.ginfo.closer.repositories.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesService {

    AdherentRepository ar;
    ServiceRepository sr;

    public ServicesService(AdherentRepository ar, ServiceRepository sr) {
        this.ar = ar;
        this.sr = sr;
    }


//    public Services addServices(Services a , Provider provider) {
//        a.getProviders().add(provider);
//        return sr.save(a);
//    }

    public Services addServices(Services a) {
        return sr.save(a);
    }


    public List<Services> services(){
        return sr.findAll();
    }

    public Services findServicesById(Long id){
        return sr.findServicesById(id)
                .orElseThrow(() -> new UserNotFoundException("Service by id " + id + "was not found"));
    }

    public Services updateServices(Services services){
        return sr.save(services);
    }

    public void deleteServices(Long id){
        sr.deleteById(id);
    }



}
