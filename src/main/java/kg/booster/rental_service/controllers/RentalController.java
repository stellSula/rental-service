package kg.booster.rental_service.controllers;

import kg.booster.rental_service.models.dtos.RentalDto;
import kg.booster.rental_service.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    @PostMapping
    public ResponseEntity<String> createRental(@RequestBody RentalDto rentalDto) {
        rentalService.createRental(rentalDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Rental operation created successfully");
    }

}
