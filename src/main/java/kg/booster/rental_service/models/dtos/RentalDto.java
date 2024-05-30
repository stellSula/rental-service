package kg.booster.rental_service.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.booster.rental_service.exeptions.BadParamsException;
import org.apache.coyote.BadRequestException;

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
        List<Map<String, Integer>> itemInventoryNumbers,
        @JsonProperty("rental_start_date")
        Date startDate,
        @JsonProperty("rental_end_date")
        Date endDate) {

    public RentalDto {
            firstname.trim();
            if (firstname.length() < 1) throw new BadParamsException("Bad " + firstname + " param!");
    }
}
