package kg.booster.rental_service.mappers.impl;

import kg.booster.rental_service.mappers.CreateRentalMapper;
import kg.booster.rental_service.models.entities.Client;
import kg.booster.rental_service.models.entities.Item;
import kg.booster.rental_service.models.entities.Rental;
import kg.booster.rental_service.models.enums.Status;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CreateRentalMapperImpl implements CreateRentalMapper {

    @Override
    public Rental rentalDtoToRental(Client client, Date startDate, Date endDate, Status status, List<Item> items) {
        Rental rental = new Rental();
        rental.setClient(client);
        rental.setStartDate(startDate);
        rental.setEndDate(endDate);
        rental.setStatus(status);
        rental.setItems(items);

        return rental;
    }

}
