package kg.booster.rental_service.mappers.impl;

import kg.booster.rental_service.mappers.ItemMapper;
import kg.booster.rental_service.models.entities.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public Item requestItemToItem(Item item, Item requestItem) {
        item.setName(requestItem.getName());
        item.setPricePerDay(requestItem.getPricePerDay());
        item.setInventoryNumber(requestItem.getInventoryNumber());

        return item;
    }

}
