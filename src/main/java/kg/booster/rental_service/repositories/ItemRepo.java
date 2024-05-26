package kg.booster.rental_service.repositories;

import kg.booster.rental_service.models.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {

    Optional<Item> findByInventoryNumber(String s);

}
