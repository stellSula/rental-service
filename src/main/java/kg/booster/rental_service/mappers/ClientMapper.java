package kg.booster.rental_service.mappers;

import kg.booster.rental_service.models.dtos.RentalDto;
import kg.booster.rental_service.models.entities.Client;

public interface ClientMapper {

    Client rentalDtoToClient(Client client, RentalDto rentalDto);

}
