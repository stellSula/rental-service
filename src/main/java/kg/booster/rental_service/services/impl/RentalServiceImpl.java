package kg.booster.rental_service.services.impl;

import kg.booster.rental_service.models.entities.Client;
import kg.booster.rental_service.models.entities.Item;
import kg.booster.rental_service.models.entities.Rental;
import kg.booster.rental_service.models.enums.Status;
import kg.booster.rental_service.models.dtos.RentalDto;
import kg.booster.rental_service.repositories.ClientRepo;
import kg.booster.rental_service.repositories.DocumentRepo;
import kg.booster.rental_service.repositories.ItemRepo;
import kg.booster.rental_service.repositories.RentalRepo;
import kg.booster.rental_service.services.ClientService;
import kg.booster.rental_service.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepo rentalRepo;

    private final ItemRepo itemRepo;

    private final ClientService clientService;

    @Override
    public void createRental(RentalDto rentalDto) {
        Client client = clientService.createOrUpdateClient(rentalDto);

        Rental rental = new Rental();
        rental.setClient(client);
        rental.setStartDate(rentalDto.startDate());
        rental.setEndDate(rentalDto.endDate());
        rental.setStatus(Status.IN_PROCESS);

        for (Map<String, Integer> itemEntry : rentalDto.itemInventoryNumbers())
            for (Map.Entry<String, Integer> entry : itemEntry.entrySet()) {
                String inventoryNumber = entry.getKey();
                int itemCount = entry.getValue();

                Optional<Item> itemOptional = itemRepo.findByInventoryNumber(inventoryNumber.trim());
                if (itemOptional.isPresent()) {
                    Item item = itemOptional.get();

                    item.setItemCount(itemCount);
                    itemRepo.save(item);
                    rental.getItems().add(item);
                }
            }

        rental.setPrice(calculateTotalPrice(rentalDto.startDate(), rentalDto.endDate(), rental.getItems()));

        rentalRepo.save(rental);
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