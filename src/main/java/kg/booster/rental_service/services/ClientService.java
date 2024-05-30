package kg.booster.rental_service.services;

import kg.booster.rental_service.models.entities.Client;
import kg.booster.rental_service.models.dtos.RentalDto;

public interface ClientService {

    Client createOrUpdateClient(RentalDto rentalDto);

}
