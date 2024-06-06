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
    public ResponseEntity<?> createRental(@RequestBody RentalDto rentalDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("\"message\" \"Rental operation created successfully\"" + "\n\"rental_id\" " + rentalService.createRental(rentalDto).getId());
    }

    @GetMapping
    public ResponseEntity<?> getRentalBy(
            @RequestParam(name = "first_name", required = false) String firstName,
            @RequestParam(name = "last_name",required = false) String lastName,
            @RequestParam(name = "middle_name",required = false) String patronymic,
            @RequestParam(name = "item_inventory_number",required = false) String itemInventoryNumber,
            @RequestParam(name = "rental_start_date",required = false) Date startDate,
            @RequestParam(name = "status",required = false) Status status
    ) {
       return null;
    }

}
