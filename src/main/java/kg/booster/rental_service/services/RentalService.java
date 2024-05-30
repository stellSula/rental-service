package kg.booster.rental_service.services;

import kg.booster.rental_service.models.entities.Rental;
import kg.booster.rental_service.models.enums.Status;
import kg.booster.rental_service.models.dtos.RentalDto;

import java.util.Date;
import java.util.List;

public interface RentalService {

    void createRental(RentalDto rentalDto);

    List<Rental> getRentalBy(String name, String lastname, String patronymic, String itemInventoryNumber, Date startDate, Status status);

}
