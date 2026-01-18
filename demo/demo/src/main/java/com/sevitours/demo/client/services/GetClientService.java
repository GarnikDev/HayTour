package com.sevitours.demo.client.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.client.Client;
import com.sevitours.demo.client.ClientDto;
import com.sevitours.demo.client.ClientMapper;
import com.sevitours.demo.client.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GetClientService implements Query<Void, List<ClientDto>> {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public GetClientService(ClientRepository clientRepository,
                            ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }


    @Override
    public ResponseEntity<List<ClientDto>> execute(Void input) {
        List<ClientDto> clientDto = clientRepository.findAll()
                .stream()
                .map(clientMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(clientDto);
    }

    public List<ClientDto> findAllClients() {
        List<ClientDto> clientDto = clientRepository.findAll()
                .stream()
                .map(clientMapper::toDto)
                .toList();

        return clientDto;
    }


    public ResponseEntity<ClientDto> execute(Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(clientMapper.toDto(client.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<List<ClientDto>> execute(String input) {
        List<Client> clientsWithName = clientRepository.findByName(input);

        /*
        if (clients.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        */
        if (!clientsWithName.isEmpty()) {
            List<ClientDto> clientDtos = clientsWithName.stream()
                    .map(clientMapper::toDto)
                    .toList();
            return ResponseEntity.ok(clientDtos);
        }
        return null;
    }

}
