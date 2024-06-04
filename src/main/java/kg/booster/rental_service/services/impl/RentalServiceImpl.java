package kg.booster.rental_service.services.impl;

import kg.booster.rental_service.models.entities.Client;
import kg.booster.rental_service.models.entities.Item;
import kg.booster.rental_service.models.entities.Rental;
import kg.booster.rental_service.models.enums.Status;
import kg.booster.rental_service.models.dtos.RentalDto;
import kg.booster.rental_service.repositories.RentalRepo;
import kg.booster.rental_service.services.ClientService;
import kg.booster.rental_service.services.ItemService;
import kg.booster.rental_service.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepo rentalRepo;

    private final ClientService clientService;

    private final ItemService itemService;

    @Override
    public Long createRental(RentalDto rentalDto) {
        Client client = clientService.createOrUpdateClient(rentalDto);

        Rental rental = new Rental();
        rental.setClient(client);
        rental.setStartDate(rentalDto.startDate());
        rental.setEndDate(rentalDto.endDate());
        rental.setStatus(Status.IN_PROCESS);
        rental.setItems(itemService.setItemsCountByInventoryNumbers(rentalDto.items()));
        rental.setPrice(calculateTotalPrice(rentalDto.startDate(), rentalDto.endDate(), rental.getItems()));

        rentalRepo.save(rental);

        return rental.getId();
    }

    @Override
    public List<Rental> getRentalBy(String name, String lastname, String patronymic, String itemInventoryNumber, Date startDate, Status status) {
        return null;
    }

    private double calculateTotalPrice(Date startDate, Date endDate, List<Item> items) {
        long rentalDays = ChronoUnit.DAYS.between(
                startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        );

        return items.stream()
                .mapToDouble(item -> (item.getPricePerDay() * rentalDays) * item.getItemCount())
                .sum();
    }

}