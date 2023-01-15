package ma.ac.emi.ginfo.closer.repositories;

import ma.ac.emi.ginfo.closer.entities.Adherent;
import ma.ac.emi.ginfo.closer.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider , Long> {

    Optional<Provider> findProviderById(Long id);

    Optional<Provider> findProviderByIdP(Long id);

//    Optional<List<Provider>> findProvidersByServicesIdContaining(Long id);


}
