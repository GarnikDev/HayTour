package com.sevitours.demo.client.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.client.model.Client;
import com.sevitours.demo.client.repo.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteClientService implements Command<Integer, Void>{

    private ClientRepository clientRepository;

    public DeleteClientService(ClientRepository clientRepository) {

        this.clientRepository = clientRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            clientRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return null;
    }
}
