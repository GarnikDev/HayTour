package com.sevitours.demo.client.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.client.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateClientService implements Command<UpdateClientCommand, ClientDto> {

    private final ClientRepository clientRepository;

    public UpdateClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ResponseEntity<ClientDto> execute(UpdateClientCommand command) {
        Optional<Client> optionalClient = clientRepository.findById(command.getId());
        if(optionalClient.isPresent()) {
            Client client = command.getClient();
            client.setId(command.getId());
            clientRepository.save(client);
            return ResponseEntity.ok(new ClientDto(client));
        }
        return null;
        //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
