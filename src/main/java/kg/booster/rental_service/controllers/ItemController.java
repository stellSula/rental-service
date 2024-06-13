package kg.booster.rental_service.controllers;

import kg.booster.rental_service.models.entities.Item;
import kg.booster.rental_service.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<?> createItem(@RequestBody Item requestItem) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("\"message\" \"Item created successfully\"" + "\n\"rental_id\" " + itemService.createItem(requestItem));
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }
}
