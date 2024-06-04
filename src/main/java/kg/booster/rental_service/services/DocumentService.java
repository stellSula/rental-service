package kg.booster.rental_service.services;

import kg.booster.rental_service.models.dtos.RentalDto;
import kg.booster.rental_service.models.entities.Client;
import kg.booster.rental_service.models.entities.Document;

public interface DocumentService {

    Document createOrUpdateDocument(Client client, RentalDto rentalDto);

}
