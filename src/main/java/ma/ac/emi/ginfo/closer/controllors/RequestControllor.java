package ma.ac.emi.ginfo.closer.controllors;

import ma.ac.emi.ginfo.closer.entities.*;
import ma.ac.emi.ginfo.closer.enumeration.State;
import ma.ac.emi.ginfo.closer.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RequestMapping("/requests")
@RestController
public class RequestControllor {

    @Autowired
    RequestService rs;

    @Autowired
    AdherentService as;

    @Autowired
    ServicesService ss;

    @Autowired
    ProviderService ps;
    @GetMapping("/find/all")
    public ResponseEntity<List<Request>> requests(){
        List<Request> requests = rs.requests();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }


//    @GetMapping("/find/{id}")
//    public Request book(@PathVariable(name = "id") Long id){
//        return rs.findRequestById(id);
//    }

    @PostMapping("/add/{idAdherent}/{idServices}")
    public ResponseEntity<Request> addRequest(@RequestBody Request request,
                                              @PathVariable("idAdherent") Long idAdherent,
                                              @PathVariable("idServices") Long idService) {
        Adherent adherent = as.findAdherentById(idAdherent);
        Services services = ss.findServicesById(idService);
        Request newRequest = rs.addRequest(adherent , services, request.getPosition(), request.getDescription());
//        adherent.getProvider().add(newRequest);
        return new ResponseEntity<>(newRequest, HttpStatus.CREATED);
    }

    @PostMapping("/addOffer/{idRequest}/{idProvider}")
    public ResponseEntity<Request> addOfferToRequest(@RequestBody Offer offer,
                                              @PathVariable("idRequest") UUID idRequest,
                                              @PathVariable("idProvider") Long idProvider) {
        Request request = rs.findRequestById(idRequest);
        Provider provider = ps.findProviderByIdP(idProvider);
        Request newRequest = rs.addOfferToRequest(request , offer, provider);
//        adherent.getProvider().add(newRequest);
        return new ResponseEntity<>(newRequest, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable("id") UUID id) {
        Request requests = rs.findRequestById(id);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }




    @GetMapping("/findCloseRequests/{id}")
    public ResponseEntity<List<Request>> getCloseRequests(@PathVariable("id") Long id) {
        List<Request> requests = rs.availableRequests();
        Provider provider = ps.findProviderByIdP(id);
        PositionService.current = provider;
        Collections.sort(requests);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @GetMapping("/findCloseRequests/byService/{id}/{idService}")
    public ResponseEntity<List<Request>> getCloseRequestsByService(@PathVariable("idService") Long idService,
                                                                     @PathVariable("id") Long id) {
        Services services = ss.findServicesById(idService);
        List<Request> requests = rs.findRequestsByServices(services);
        Provider provider = ps.findProviderByIdP(id);
        PositionService.current = provider;
        Collections.sort(requests);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }


    @PostMapping("/chooseOffer/{idRequest}/{idOffer}")
    public ResponseEntity<Request> chooseOffer(@PathVariable("idRequest") UUID idRequest,
                                                                   @PathVariable("idOffer") UUID idOffer) {
        Request request = rs.findRequestById(idRequest);
        Offer offer = rs.findOfferById(idOffer);
        request.setState(State.ACCEPTED);
        request.setDateAccepted(LocalDate.now());
        request.setOfferSelected(offer);
        request.setOffers(null);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }







    @PutMapping("/update")
    public ResponseEntity<Request> updateRequest(@RequestBody Request adherent) {
        Request updateRequest = rs.updateRequest(adherent);
        return new ResponseEntity<>(updateRequest, HttpStatus.OK);
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<Request> acceptRequest(@PathVariable("id") UUID id) {
        Request book = rs.findRequestById(id);
        rs.acceptRequest(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("/finish/{id}")
    public ResponseEntity<Request> finishRequest(@PathVariable("id") UUID id) {
        Request book = rs.findRequestById(id);
        rs.finishRequest(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("/refuse/{id}")
    public ResponseEntity<Request> refuseRequest(@PathVariable("id") UUID id) {
        Request book = rs.findRequestById(id);
        rs.refuseRequest(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteRequest(@PathVariable("id") UUID id) {
//        rs.deleteRequest(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}

