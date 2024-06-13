package kg.booster.rental_service.services.impl;

import kg.booster.rental_service.mappers.ClientMapper;
import kg.booster.rental_service.models.entities.Client;
import kg.booster.rental_service.models.dtos.RentalDto;
import kg.booster.rental_service.repositories.ClientRepo;
import kg.booster.rental_service.services.ClientService;
import kg.booster.rental_service.services.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepo clientRepo;

    private final DocumentService documentService;

    private final ClientMapper clientMapper;

    @Override
    public Client createOrUpdateClient(RentalDto rentalDto) {
        Client client = clientRepo.findByInn(rentalDto.inn()).orElseGet(Client::new);

        client = clientMapper.rentalDtoToClient(client, rentalDto);

        client.setDocument(documentService.createOrUpdateDocument(client, rentalDto));

        return clientRepo.save(client);
    }

}
