package ma.ac.emi.ginfo.closer.repositories;

import ma.ac.emi.ginfo.closer.entities.Adherent;
import ma.ac.emi.ginfo.closer.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdherentRepository extends JpaRepository<Adherent, Long> {
    Optional<Adherent> findAdherentById(Long id);

}