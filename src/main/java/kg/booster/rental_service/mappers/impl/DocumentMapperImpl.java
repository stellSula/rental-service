package kg.booster.rental_service.mappers.impl;

import kg.booster.rental_service.mappers.DocumentMapper;
import kg.booster.rental_service.models.dtos.RentalDto;
import kg.booster.rental_service.models.entities.Client;
import kg.booster.rental_service.models.entities.Document;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapperImpl implements DocumentMapper {

    @Override
    public Document rentalDtoToDocument(Client client, RentalDto rentalDto) {
        Document document = client.getDocument() == null ? new Document() : client.getDocument();
        document.setSeries(rentalDto.series());
        document.setNumber(rentalDto.number());

        return document;
    }

}
