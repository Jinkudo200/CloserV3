package ma.ac.emi.ginfo.closer.controllors;

import ma.ac.emi.ginfo.closer.entities.Adherent;
import ma.ac.emi.ginfo.closer.entities.Book;
import ma.ac.emi.ginfo.closer.entities.Provider;
import ma.ac.emi.ginfo.closer.entities.Services;
import ma.ac.emi.ginfo.closer.services.AdherentService;
import ma.ac.emi.ginfo.closer.services.ProviderService;
import ma.ac.emi.ginfo.closer.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/adherents")
@RestController
public class AdherentControllor {

    @Autowired
    AdherentService as;

    @Autowired
    ServicesService ss;

    @Autowired
    ProviderService ps;

    @GetMapping("/find/all")
    public ResponseEntity<List<Adherent>> adherents(){
        List<Adherent> adherents = as.adherents();
        return new ResponseEntity<>(adherents, HttpStatus.OK);
    }

    @GetMapping("/find/allP")
    public ResponseEntity<List<Provider>> providers(){
        List<Provider> providers = ps.providers();
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }

    @GetMapping("/find/byService/{idService}")
    public ResponseEntity<List<Provider>> getProvidersByService(@PathVariable("idService") Long id) {
        Services services = ss.findServicesById(id);
        List<Provider> providers = services.getProviders();
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }

//    @GetMapping("/d/{id}")
//    public Adherent adherent(@PathVariable(name = "id") Long id){
//        return as.findAdherentById(id);
//    }

    @PostMapping("/add")
    public ResponseEntity<Adherent> addAdherent(@RequestBody Adherent adherent) {
        Adherent newAdherent = as.addAdherent(adherent);
        return new ResponseEntity<>(newAdherent, HttpStatus.CREATED);
    }

    @PostMapping("/addP/{idAdherent}/{idServices}")
    public ResponseEntity<Provider> addProvider(@PathVariable("idAdherent") Long idAdherent,
                                                @PathVariable("idServices") Long idService) {
        Adherent adherent = as.findAdherentById(idAdherent);
        Services services = ss.findServicesById(idService);
        Provider provider = ps.becomeProvider(adherent , services);
        return new ResponseEntity<>(provider, HttpStatus.CREATED);
    }

    @PostMapping("/add/favoris/{idAdherent}/{idServices}")
    public ResponseEntity<Adherent> addServiceToFavoris(@PathVariable("idAdherent") Long idAdherent,
                                        @PathVariable("idServices") Long idProvider) {
        Adherent adherent = as.findAdherentById(idAdherent);
        Provider provider = ps.findProviderById(idProvider);
        Adherent newAdherent = as.addServiceToFavoris(provider, adherent);
        return new ResponseEntity<>(newAdherent, HttpStatus.CREATED);
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<Adherent> getAdherentById(@PathVariable("id") Long id) {
        Adherent adherent = as.findAdherentById(id);
        return new ResponseEntity<>(adherent, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Adherent> updateAdherent(@RequestBody Adherent adherent) {
        Adherent updateAdherent = as.updateAdherent(adherent);
        return new ResponseEntity<>(updateAdherent, HttpStatus.OK);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAdherent(@PathVariable("id") Long id) {
        as.deleteAdherent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}