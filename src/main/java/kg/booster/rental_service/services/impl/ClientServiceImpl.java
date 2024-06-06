package kg.booster.rental_service.services.impl;

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

    @Override
    public Client createOrUpdateClient(RentalDto rentalDto) {
        Client client = clientRepo.findByInn(rentalDto.inn()).orElseGet(Client::new);

        client.setFirstname(rentalDto.firstname());
        client.setLastname(rentalDto.lastname());
        client.setPatronymic(rentalDto.patronymic());
        client.setInn(rentalDto.inn());
        client.setAddress(rentalDto.address());
        client.setDocument(documentService.createOrUpdateDocument(client, rentalDto));

        return clientRepo.save(client);
    }

}
