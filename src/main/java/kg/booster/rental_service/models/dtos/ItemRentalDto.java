package kg.booster.rental_service.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ItemRentalDto(
        @JsonProperty("inventory_number")
        String inventoryNumber,
        @JsonProperty("item_count")
        int itemCount
        ) {

}
