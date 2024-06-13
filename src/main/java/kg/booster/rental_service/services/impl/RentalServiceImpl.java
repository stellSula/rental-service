package kg.booster.rental_service.services.impl;

import kg.booster.rental_service.mappers.CreateRentalMapper;
import kg.booster.rental_service.models.dtos.ResponseCustomRentalDto;
import kg.booster.rental_service.models.entities.Item;
import kg.booster.rental_service.models.entities.Rental;
import kg.booster.rental_service.models.enums.Status;
import kg.booster.rental_service.models.dtos.RentalDto;
import kg.booster.rental_service.repositories.RentalRepo;
import kg.booster.rental_service.services.ClientService;
import kg.booster.rental_service.services.ItemService;
import kg.booster.rental_service.services.RentalService;
import kg.booster.rental_service.specifications.RentalSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepo rentalRepo;

    private final ClientService clientService;

    private final ItemService itemService;

    private final CreateRentalMapper createRentalMapper;

    @Override
    public Long createRental(RentalDto rentalDto) {
        Rental rental = createRentalMapper.rentalDtoToRental(
                clientService.createOrUpdateClient(rentalDto),
                rentalDto.startDate(),
                rentalDto.endDate(),
                Status.IN_PROCESS,
                itemService.setItemsCountByInventoryNumbers(rentalDto.items())
        );
        rental.setPrice(calculateTotalPrice(rentalDto.startDate(), rentalDto.endDate(), rental.getItems()));
        rental = rentalRepo.save(rental);

        return rental.getId();
    }

    @Override
    public List<ResponseCustomRentalDto> getRentals(String firstname, String lastname, String patronymic, String itemInventoryNumber, Date startDate, Status status) {
        Specification<Rental> specification = Specification
                .where(RentalSpecifications.hasClientName(firstname))
                .and(RentalSpecifications.hasClientLastname(lastname))
                .and(RentalSpecifications.hasClientPatronymic(patronymic))
                .and(RentalSpecifications.hasItemInventoryNumber(itemInventoryNumber))
                .and(RentalSpecifications.hasRentalStartDate(startDate))
                .and(RentalSpecifications.hasRentalStatus(status));

        List<Rental> rentals = rentalRepo.findAll(specification);

        return rentals.stream()
                .map(rental -> new ResponseCustomRentalDto(
                        rental.getClient().getFirstname(),
                        rental.getClient().getLastname(),
                        rental.getClient().getPatronymic(),
                        rental.getClient().getInn(),
                        rental.getClient().getDocument().getSeries(),
                        rental.getClient().getDocument().getNumber(),
                        rental.getClient().getAddress(),
                        rental.getItems(),
                        rental.getStartDate(),
                        rental.getEndDate(),
                        rental.getStatus()
                ))
                .collect(Collectors.toList());
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