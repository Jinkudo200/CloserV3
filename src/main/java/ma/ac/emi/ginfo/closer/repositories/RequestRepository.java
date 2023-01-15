package ma.ac.emi.ginfo.closer.repositories;

import ma.ac.emi.ginfo.closer.entities.Adherent;
import ma.ac.emi.ginfo.closer.entities.Request;
import ma.ac.emi.ginfo.closer.entities.Services;
import ma.ac.emi.ginfo.closer.enumeration.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RequestRepository extends JpaRepository<Request, UUID> {

    Optional<Request> findRequestById(UUID id);

    List<Request> findRequestsByAdherent(Adherent a);

    List<Request> findRequestsByState(State state);

    List<Request> findRequestsByServices(Services services);


    void deleteById(UUID id);
}
