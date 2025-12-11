package com.sevitours.demo.client.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.client.ClientDto;
import com.sevitours.demo.client.ClientMapper;
import com.sevitours.demo.client.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

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
}
