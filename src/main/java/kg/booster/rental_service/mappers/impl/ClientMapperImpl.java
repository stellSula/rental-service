package kg.booster.rental_service.mappers.impl;

import kg.booster.rental_service.mappers.ClientMapper;
import kg.booster.rental_service.models.dtos.RentalDto;
import kg.booster.rental_service.models.entities.Client;
import kg.booster.rental_service.services.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor

@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client rentalDtoToClient(Client client, RentalDto rentalDto) {
        client.setFirstname(rentalDto.firstname());
        client.setLastname(rentalDto.lastname());
        client.setPatronymic(rentalDto.patronymic());
        client.setInn(rentalDto.inn());
        client.setAddress(rentalDto.address());

        return client;
    }
}
