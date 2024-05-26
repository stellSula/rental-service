package kg.booster.rental_service.services.impl;

import kg.booster.rental_service.models.dtos.RentalDto;
import kg.booster.rental_service.models.entities.Client;
import kg.booster.rental_service.models.entities.Document;
import kg.booster.rental_service.models.entities.Item;
import kg.booster.rental_service.models.entities.Rental;
import kg.booster.rental_service.models.enums.Status;
import kg.booster.rental_service.repositories.ClientRepo;
import kg.booster.rental_service.repositories.DocumentRepo;
import kg.booster.rental_service.repositories.ItemRepo;
import kg.booster.rental_service.repositories.RentalRepo;
import kg.booster.rental_service.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepo rentalRepo;

    private final ClientRepo clientRepo;

    private final ItemRepo itemRepo;

    private final DocumentRepo documentRepo;

    @Override
    public void createRental(RentalDto rentalDto) {
        Optional<Client> clientOptional = clientRepo.findByInn(rentalDto.getInn());
        Client client;

        if (clientOptional.isPresent()) {
            client = clientOptional.get();

            client.setFirstname(rentalDto.getFirstname());
            client.setLastname(rentalDto.getLastname());
            client.setPatronymic(rentalDto.getPatronymic());
            client.setInn(rentalDto.getInn());
            client.setAddress(rentalDto.getAddress());

            Document document = client.getDocument();
            document.setSeries(rentalDto.getSeries());
            document.setNumber(rentalDto.getNumber());
            documentRepo.save(document);
        } else {
            Document document = new Document();
            document.setSeries(rentalDto.getSeries());
            document.setNumber(rentalDto.getNumber());
            documentRepo.save(document);

            client = new Client();
            client.setFirstname(rentalDto.getFirstname());
            client.setLastname(rentalDto.getLastname());
            client.setPatronymic(rentalDto.getPatronymic());
            client.setInn(rentalDto.getInn());
            client.setAddress(rentalDto.getAddress());
            client.setDocument(document);
        }
        clientRepo.save(client);

        Rental rental = new Rental();
        rental.setClient(client);
        rental.setStartDate(rentalDto.getStartDate());
        rental.setEndDate(rentalDto.getEndDate());
        rental.setStatus(Status.IN_PROCESS);

        for (String inventoryNumber : rentalDto.getInventoryNumbers()) {
            Optional<Item> itemOptional = itemRepo.findByInventoryNumber(inventoryNumber.trim());
            if (itemOptional.isPresent()) {
                Item item = itemOptional.get();
                rental.getItems().add(item);
            }
        }

        rental.setPrice(calculateTotalPrice(rentalDto.getStartDate(), rentalDto.getEndDate(), rental.getItems()));

        rentalRepo.save(rental);
    }

    private double calculateTotalPrice(Date startDate, Date endDate, List<Item> items) {
        long rentalDays = ChronoUnit.DAYS.between(
                startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        );

        return items.stream()
                .mapToDouble(item -> item.getPricePerDay() * rentalDays)
                .sum();
    }

    private List<Item> getItems() {
        return itemRepo.findAll();
    }
}



//        rentalDto.getInventoryNumbers().stream()
//                .map(String::trim)
//                .map(itemRepo::findByInventoryNumber)
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .forEach(rental.getItems()::add);
