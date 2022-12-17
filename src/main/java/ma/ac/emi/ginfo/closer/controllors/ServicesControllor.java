package ma.ac.emi.ginfo.closer.controllors;

import ma.ac.emi.ginfo.closer.entities.Adherent;
import ma.ac.emi.ginfo.closer.entities.Services;
import ma.ac.emi.ginfo.closer.services.AdherentService;
import ma.ac.emi.ginfo.closer.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RequestMapping("/services")
@RestController
public class ServicesControllor {

    @Autowired
    ServicesService ss;

    @Autowired
    AdherentService as;

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
