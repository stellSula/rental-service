package kg.booster.rental_service.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.booster.rental_service.models.dtos.RentalDto;
import kg.booster.rental_service.models.entities.Rental;
import kg.booster.rental_service.models.enums.Status;
import kg.booster.rental_service.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    @PostMapping
    public ResponseEntity<String> createRental(@RequestBody RentalDto rentalDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Rental operation created successfully" + rentalService.createRental(rentalDto));
    }

    @GetMapping
    public ResponseEntity<List<Rental>> getRentalBy(
            @RequestParam(required = false) @JsonProperty("first_name") String firstName,
            @RequestParam(required = false) @JsonProperty("last_name") String lastName,
            @RequestParam(required = false) @JsonProperty("middle_name") String patronymic,
            @RequestParam(required = false) @JsonProperty("item_inventory_name") String itemInventoryNumber,
            @RequestParam(required = false) @JsonProperty("rental_start_date") Date startDate,
            @RequestParam(required = false) @JsonProperty("status") Status status
    ) {
        return ResponseEntity.ok(rentalService.getRentalBy(
                firstName,
                lastName,
                patronymic,
                itemInventoryNumber,
                startDate,
                status)
        );
    }

}
