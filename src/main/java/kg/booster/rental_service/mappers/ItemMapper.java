package kg.booster.rental_service.mappers;

import kg.booster.rental_service.models.entities.Item;

public interface ItemMapper {

    Item requestItemToItem(Item item, Item requestItem);

}
