package kg.booster.rental_service.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor

public class RentalDto {

    @JsonProperty("first_name")
    String firstname;

    @JsonProperty("last_name")
    String lastname;

    @JsonProperty("middle_name")
    String patronymic;

    String inn;

    @JsonProperty("passport_series")
    String series;

    @JsonProperty("passport_number")
    String number;

    @JsonProperty("home_address")
    String address;

    @JsonProperty("item_inventory_numbers")
    List<String> inventoryNumbers;

    @JsonProperty("rental_start_date")
    Date startDate;

    @JsonProperty("rental_end_date")
    Date endDate;

}
