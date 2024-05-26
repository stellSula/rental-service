package kg.booster.rental_service.services;

import kg.booster.rental_service.models.dtos.RentalDto;
import kg.booster.rental_service.models.entities.Rental;

public interface RentalService {

    void createRental(RentalDto rentalDto);

}
