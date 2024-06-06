package kg.booster.rental_service.services.impl;

import kg.booster.rental_service.models.dtos.RentalDto;
import kg.booster.rental_service.models.entities.Client;
import kg.booster.rental_service.models.entities.Document;
import kg.booster.rental_service.repositories.DocumentRepo;
import kg.booster.rental_service.services.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepo documentRepo;

    @Override
    public Document createOrUpdateDocument(Client client, RentalDto rentalDto) {
        Document document = client.getDocument() == null ? new Document() : client.getDocument();

        document.setSeries(rentalDto.series());
        document.setNumber(rentalDto.number());

        return documentRepo.save(document);
    }

}
