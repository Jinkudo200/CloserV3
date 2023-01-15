package ma.ac.emi.ginfo.closer.services;

import ma.ac.emi.ginfo.closer.entities.*;
import ma.ac.emi.ginfo.closer.enumeration.State;
import ma.ac.emi.ginfo.closer.exceptions.UserNotFoundException;
import ma.ac.emi.ginfo.closer.repositories.RequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Service
public class RequestService {

    RequestRepository rr;

    public RequestService(RequestRepository rr) {
        this.rr = rr;
    }

    public Request addRequest(Adherent a, Services services, Position position, String description) {
        Request request = new Request(a, services, position, description);
        return rr.save(request);
    }

    public Request addRequest(Request b) {
        return rr.save(b);
    }

    public Request acceptRequest(Request request) {
        request.setDateAccepted(LocalDate.now());
        request.setState(State.ACCEPTED);
        return rr.save(request);
    }

    public Request finishRequest(Request request) {
        request.setDateDone(LocalDate.now());
        request.setState(State.DONE);
        return rr.save(request);
    }

    public Request refuseRequest(Request request) {
        request.setState(State.REFUSED);
        return rr.save(request);
    }


    public List<Request> requests() {
        return rr.findAll();
    }

    public List<Request> availableRequests() {
        return rr.findRequestsByState(State.TREATING);
    }

    public List<Request> findRequestsByServices(Services services) {
        return rr.findRequestsByServices(services);
    }

    public Request findRequestById(UUID id) {
        return rr.findRequestById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + "was not found"));
    }

    public Request updateRequest(Request request) {
        return rr.save(request);
    }

    public void deleteRequest(UUID id) {
        rr.deleteById(id);
    }

    public List<Request> findRequestsByAdherent(Adherent adherent) {
        return rr.findRequestsByAdherent(adherent);
    }

    public Request addOfferToRequest(Request request, Offer offer, Provider provider) {
        Offer newOffer = new Offer(request , offer.getDescription(), provider);
//        System.out.println(newOffer);
        request.getOffers().add(newOffer);
//        System.out.println(request.getOffers());
        return rr.save(request);
    }


}
