package kg.booster.rental_service.mappers;

import kg.booster.rental_service.models.entities.Client;
import kg.booster.rental_service.models.entities.Item;
import kg.booster.rental_service.models.entities.Rental;
import kg.booster.rental_service.models.enums.Status;

import java.util.Date;
import java.util.List;

public interface CreateRentalMapper {

    Rental rentalDtoToRental(Client orUpdateClient, Date date, Date date1, Status status, List<Item> items);

}
