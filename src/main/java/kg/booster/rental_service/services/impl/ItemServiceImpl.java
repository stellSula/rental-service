package kg.booster.rental_service.services.impl;

import kg.booster.rental_service.models.dtos.ItemRentalDto;
import kg.booster.rental_service.models.entities.Item;
import kg.booster.rental_service.repositories.ItemRepo;
import kg.booster.rental_service.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepo itemRepo;

    @Override
    public void createItem(Item requestItem) {
        Item item = itemRepo.findByInventoryNumber(requestItem.getInventoryNumber()).orElseGet(() -> new Item());

        item.setName(requestItem.getName());
        item.setPricePerDay(requestItem.getPricePerDay());
        item.setInventoryNumber(requestItem.getInventoryNumber());
        itemRepo.save(item);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }

    @Override
    public List<Item> setItemsCountByInventoryNumbers(List<ItemRentalDto> items) {
        List<String> inventoryNumbers = new ArrayList<>();
        for (ItemRentalDto itemRentalDto : items) {
            inventoryNumbers.add(itemRentalDto.inventoryNumber());
        }

        List<Item> foundItems = itemRepo.findByInventoryNumberIn(inventoryNumbers);

        Map<String, Item> itemMap = foundItems.stream()
                .collect(Collectors.toMap(Item::getInventoryNumber, item -> item));

        List<Item> updatedItems = new ArrayList<>();

        for (ItemRentalDto rentalDtoItem : items) {
            Item item = itemMap.get(rentalDtoItem.inventoryNumber());

            item.setItemCount(rentalDtoItem.itemCount());
            updatedItems.add(itemRepo.save(item));
        }

        return updatedItems;
    }

}
