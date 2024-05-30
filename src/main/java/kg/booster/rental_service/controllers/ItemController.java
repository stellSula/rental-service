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
    public ResponseEntity<String> createItem(@RequestBody Item requestItem) {
        itemService.createItem(requestItem);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Item created successfully");
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }
}
