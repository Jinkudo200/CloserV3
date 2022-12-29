package ma.ac.emi.ginfo.closer.controllors;

import ma.ac.emi.ginfo.closer.entities.Adherent;
import ma.ac.emi.ginfo.closer.entities.Provider;
import ma.ac.emi.ginfo.closer.entities.Services;
import ma.ac.emi.ginfo.closer.services.AdherentService;
import ma.ac.emi.ginfo.closer.services.PositionService;
import ma.ac.emi.ginfo.closer.services.ProviderService;
import ma.ac.emi.ginfo.closer.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
@CrossOrigin
@RequestMapping("/services")
@RestController
public class ServicesControllor {

    @Autowired
    ServicesService ss;

    @Autowired
    AdherentService as;

    @Autowired
    ProviderService ps;

    @Autowired
    PositionService pps;

    @GetMapping("/find/all")
    public List<Services> services(){
        return ss.services();
    }

//    @GetMapping("/{id}")
//    public Services services(@PathVariable(name = "id") Long id){
//        return ss.findServicesById(id);
//    }

    @PostMapping("/add")
    public ResponseEntity<Services> addServices(@RequestBody Services services) {
        Services newServices = ss.addServices(services);
        return new ResponseEntity<>(newServices, HttpStatus.CREATED);
    }

//    @PostMapping("/services/add")
//    public ResponseEntity<Services> addServices(@RequestBody Services services) {
//        Services newServices = ss.addServices(services);
//        return new ResponseEntity<>(newServices, HttpStatus.CREATED);
//    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Services> getServicesById(@PathVariable("id") Long id) {
        Services services = ss.findServicesById(id);
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @GetMapping("/findCloseProviders/{id}")
    public ResponseEntity<List<Provider>> getCloseProviders(@PathVariable("id") Long id) {
        List<Provider> providers = ps.providers();
        Adherent adherent = as.findAdherentById(id);
//        PositionService.current = adherent;
        Collections.sort(providers);
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }

    @GetMapping("/findCloseProviders/byService/{id}/{idService}")
    public ResponseEntity<List<Provider>> getCloseProvidersByService(@PathVariable("idService") Long idService,
                                                                     @PathVariable("id") Long id) {
        Services services = ss.findServicesById(idService);
        List<Provider> providers = services.getProviders();
        Adherent adherent = as.findAdherentById(id);
//        PositionService.current = adherent;
        Collections.sort(providers);
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Services> updateServices(@RequestBody Services services) {
        Services updateServices = ss.updateServices(services);
        return new ResponseEntity<>(updateServices, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteServices(@PathVariable("id") Long id) {
        ss.deleteServices(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
