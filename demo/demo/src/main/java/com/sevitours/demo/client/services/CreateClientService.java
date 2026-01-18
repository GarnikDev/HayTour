package com.sevitours.demo.client.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.client.Client;
import com.sevitours.demo.client.ClientDto;
import com.sevitours.demo.client.ClientMapper;
import com.sevitours.demo.client.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateClientService implements Command<Client, ClientDto> {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public CreateClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ResponseEntity<ClientDto> execute(Client client) {
        Client savedClient = clientRepository.save(client);
        ClientDto clientDto = clientMapper.toDto(savedClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientDto);
    }
}
