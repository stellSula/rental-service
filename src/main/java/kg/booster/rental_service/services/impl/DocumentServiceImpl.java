package kg.booster.rental_service.services.impl;

import kg.booster.rental_service.mappers.DocumentMapper;
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

    private final DocumentMapper documentMapper;

    @Override
    public Document createOrUpdateDocument(Client client, RentalDto rentalDto) {
        return documentRepo.save(documentMapper.rentalDtoToDocument(client, rentalDto));
    }

}
