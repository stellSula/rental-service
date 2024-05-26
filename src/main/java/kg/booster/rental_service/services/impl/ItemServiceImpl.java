package kg.booster.rental_service.services.impl;

import kg.booster.rental_service.models.entities.Item;
import kg.booster.rental_service.repositories.ItemRepo;
import kg.booster.rental_service.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
            item.setItemCount(requestItem.getItemCount());
        }

        itemRepo.save(item);
    }

    @Override
    public List<Item> getALlItems() {
        return itemRepo.findAll();
    }

}
