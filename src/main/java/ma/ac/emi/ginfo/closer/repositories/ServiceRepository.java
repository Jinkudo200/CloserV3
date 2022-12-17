package ma.ac.emi.ginfo.closer.repositories;

import ma.ac.emi.ginfo.closer.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Services, Long> {

    Optional<Services> findServicesById(Long id);

}
