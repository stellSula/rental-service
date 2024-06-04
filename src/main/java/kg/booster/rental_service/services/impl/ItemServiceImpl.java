package kg.booster.rental_service.services.impl;

import kg.booster.rental_service.models.dtos.ItemRentalDto;
import kg.booster.rental_service.models.entities.Item;
import kg.booster.rental_service.repositories.ItemRepo;
import kg.booster.rental_service.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepo itemRepo;

    @Override
    public void createItem(Item requestItem) {
        Optional<Item> itemOptional = itemRepo.findByInventoryNumber(requestItem.getInventoryNumber());
        Item item;
        if (itemOptional.isPresent()) {
            item = itemOptional.get();

            item.setName(requestItem.getName());
            item.setPricePerDay(requestItem.getPricePerDay());
            item.setItemCount(requestItem.getItemCount());

            itemRepo.save(item);
        } else {
            item = new Item();

            item.setName(requestItem.getName());
            item.setPricePerDay(requestItem.getPricePerDay());
            item.setInventoryNumber(requestItem.getInventoryNumber());
        }

        itemRepo.save(item);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }

    @Override
    public List<Item> setItemsCountByInventoryNumbers(List<ItemRentalDto> items) {
        List<Item> newItems = new ArrayList<>();
        for (ItemRentalDto rentalDtoItem : items) {
            Optional<Item> optionalItem = itemRepo.findByInventoryNumber(rentalDtoItem.inventoryNumber());

            if (optionalItem.isPresent()) {
                Item item = optionalItem.get();

                item.setItemCount(rentalDtoItem.itemCount());
                newItems.add(itemRepo.save(item));
            }
        }

        return newItems;
    }

}
