package ma.ac.emi.ginfo.closer.repositories;

import ma.ac.emi.ginfo.closer.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating , Long> {

    Optional<Rating> findRatingById(Long id);
}
