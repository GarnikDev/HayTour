package com.sevitours.demo.client.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.client.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetClientByNameService implements Query<String, List<ClientDto>> {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public GetClientByNameService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ResponseEntity<List<ClientDto>> execute(String input) {
        List<Client> clientsWithName = clientRepository.findByName(input);

        /*
        if (clients.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        */
        if(!clientsWithName.isEmpty()){
            List<ClientDto> clientDtos = clientsWithName.stream()
                    .map(clientMapper::toDto)
                    .toList();
            return ResponseEntity.ok(clientDtos);
        }
        return null;
    }
}
