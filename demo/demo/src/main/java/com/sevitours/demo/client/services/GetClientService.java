package com.sevitours.demo.client.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.client.model.Client;
import com.sevitours.demo.client.model.ClientDto;
import com.sevitours.demo.client.model.ClientMapper;
import com.sevitours.demo.client.repo.ClientRepository;
import com.sevitours.demo.language.repo.LanguageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class GetClientService implements Query<Void, List<ClientDto>> {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final LanguageRepository languageRepository;


    public GetClientService(ClientRepository clientRepository,
                            ClientMapper clientMapper,
                            LanguageRepository languageRepository) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.languageRepository = languageRepository;
    }


    @Override
    public ResponseEntity<List<ClientDto>> execute(Void input) { // returns All existing clients
        List<ClientDto> clientDto = clientRepository.findAll()
                .stream()
                .map(clientMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(clientDto);
    }

    public ResponseEntity<ClientDto> execute(Integer id) { // Returns the client with inputed id
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(clientMapper.toDto(client.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
