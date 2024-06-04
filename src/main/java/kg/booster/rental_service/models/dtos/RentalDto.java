package kg.booster.rental_service.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.booster.rental_service.exeptions.BadParamsException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

public record RentalDto(
        @JsonProperty("first_name")
        String firstname,
        @JsonProperty("last_name")
        String lastname,
        @JsonProperty("middle_name")
        String patronymic,
        String inn,
        @JsonProperty("passport_series")
        String series,
        @JsonProperty("passport_number")
        String number,
        @JsonProperty("home_address")
        String address,
        @JsonProperty("item_inventory_numbers")
        List<ItemRentalDto> items,
        @JsonProperty("rental_start_date")
        Date startDate,
        @JsonProperty("rental_end_date")
        Date endDate) {

//    public RentalDto {
//        if (isInvalid(firstname)) {
//            throw new BadParamsException("The firstname field must contain at least 1 character and only letters");
//        }
//        if (isInvalid(lastname)) {
//            throw new BadParamsException("The lastname field must contain at least 1 character and only letters");
//        }
//        if (isInvalid(patronymic)) {
//            throw new BadParamsException("The patronymic field must contain at least 1 character and only letters");
//        }
//        if (series == null || series.isEmpty()) {
//            throw new BadParamsException("The series field cannot be empty!");
//        }
//        if (number == null || number.isEmpty()) {
//            throw new BadParamsException("The number field cannot be empty!");
//        }
//
//        firstname = firstname.trim();
//        lastname = lastname.trim();
//        patronymic = patronymic.trim();
//
//        System.out.println("startDate" + startDate);
//        System.out.println("endDate" + endDate);
//        isNotCurrentDate(startDate);
//        isBeforeEndDate(startDate, endDate);
//    }

    private static void isBeforeEndDate(Date startDate, Date endDate) {
        LocalDate givenStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate givenEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (!givenStartDate.isBefore(givenEndDate)) {
            throw new BadParamsException("Start date cannot be larger than the current date");
        }
    }

    private static void isNotCurrentDate(Date date) {
        LocalDate givenDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();

        if (!givenDate.isEqual(currentDate)) {
            throw new BadParamsException("Start date cannot be earlier than the current date");
        }
    }

    private static boolean isInvalid(String value) {
        return value == null || value.length() < 1 || !value.matches("[a-zA-Zа-яА-Я]+");
    }

}
