package ma.ac.emi.ginfo.closer.controllors;

import ma.ac.emi.ginfo.closer.entities.*;
import ma.ac.emi.ginfo.closer.services.*;
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

    @Autowired
    RequestService rs;



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

    @GetMapping("/login/{id}")
    public ResponseEntity<Adherent> logIn(@PathVariable("id") Long id) {
        Adherent adherent = as.findAdherentById(id);
        PositionService.current = adherent;
        return new ResponseEntity<>(adherent, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<Adherent> logout() {
        PositionService.current = null;
        return new ResponseEntity<>(HttpStatus.OK);
    }



//    @GetMapping("/d/{id}")
//    public Adherent adherent(@PathVariable(name = "id") Long id){
//        return as.findAdherentById(id);
//    }

    @PostMapping("/add")
    public ResponseEntity<Adherent> addAdherent(@RequestBody Adherent adherent) {
        Adherent newAdherent = as.addAdherent(adherent);
//        newAdherent.getPosition().setAdherent(newAdherent);
        return new ResponseEntity<>(newAdherent, HttpStatus.CREATED);
    }

    @PostMapping("/addP/{idAdherent}/{idServices}")
    public ResponseEntity<Provider> addProvider(@PathVariable("idAdherent") Long idAdherent,
                                                @PathVariable("idServices") Long idService) {
        Adherent adherent = as.findAdherentById(idAdherent);
        Services services = ss.findServicesById(idService);
        List<Request> requests = rs.findRequestsByAdherent(adherent);
        Provider provider = ps.becomeProvider(adherent , services);
        for (Request b : requests) {
            b.setAdherent(provider);
        }
        return new ResponseEntity<>(provider, HttpStatus.CREATED);
    }

    @PostMapping("/add/favoris/{idAdherent}/{idServices}")
    public ResponseEntity<Adherent> addServiceToFavoris(@PathVariable("idAdherent") Long idAdherent,
                                        @PathVariable("idServices") Long idProvider) {
        Adherent adherent = as.findAdherentById(idAdherent);
        Provider provider = ps.findProviderByIdP(idProvider);
        Adherent newAdherent = as.addServiceToFavoris(provider, adherent);
        return new ResponseEntity<>(newAdherent, HttpStatus.CREATED);
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<Adherent> getAdherentById(@PathVariable("id") Long id) {
        Adherent adherent = as.findAdherentById(id);
        return new ResponseEntity<>(adherent, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<Adherent> login(@RequestBody Compte compte) {
        Adherent adherent = as.findAdherentByEmailAndPassword(compte.getEmail(), compte.getPassword());
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