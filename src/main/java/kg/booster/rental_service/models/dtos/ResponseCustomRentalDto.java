package kg.booster.rental_service.models.dtos;

import kg.booster.rental_service.models.entities.Item;
import kg.booster.rental_service.models.enums.Status;

import java.util.Date;
import java.util.List;

public record ResponseCustomRentalDto(
        String firstname,
        String lastname,
        String patronymic,
        String inn,
        String series,
        String number,
        String address,
        List<Item> items,
        Date startDate,
        Date endDate,
        Status status) {

}
