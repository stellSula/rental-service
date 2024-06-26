package kg.booster.rental_service.repositories;

import kg.booster.rental_service.models.entities.Rental;
import kg.booster.rental_service.models.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepo extends JpaRepository<Rental, Long>, JpaSpecificationExecutor<Rental> {

}
