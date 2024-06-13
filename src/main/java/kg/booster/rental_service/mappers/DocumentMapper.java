package kg.booster.rental_service.mappers;

import kg.booster.rental_service.models.dtos.RentalDto;
import kg.booster.rental_service.models.entities.Client;
import kg.booster.rental_service.models.entities.Document;

public interface DocumentMapper {

    Document rentalDtoToDocument(Client client, RentalDto rentalDto);

}
