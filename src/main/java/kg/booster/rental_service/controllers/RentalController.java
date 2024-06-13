package kg.booster.rental_service.controllers;

import kg.booster.rental_service.models.dtos.RentalDto;
import kg.booster.rental_service.models.enums.Status;
import kg.booster.rental_service.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequiredArgsConstructor

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    @PostMapping
    public ResponseEntity<?> createRental(@RequestBody RentalDto rentalDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("\"message\" \"Rental operation created successfully\"" + "\n\"rental_id\" " + rentalService.createRental(rentalDto));
    }

    @GetMapping
    public ResponseEntity<?> getRentalBy(
            @RequestParam(required = false) String firstname,
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false) String patronymic,
            @RequestParam(required = false) String itemInventoryNumber,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Status status) {

        return ResponseEntity.ok(rentalService.getRentals(
                firstname,
                lastname,
                patronymic,
                itemInventoryNumber,
                startDate,
                status)
        );
    }

}
