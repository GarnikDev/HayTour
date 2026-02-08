package com.sevitours.demo.client.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.client.Client;
import com.sevitours.demo.client.ClientDto;
import com.sevitours.demo.client.ClientMapper;
import com.sevitours.demo.client.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public class CreateClientService implements Command<Client, ClientDto> {

    private final ClientRepository clientRepository;
    public CreateClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ResponseEntity<ClientDto> execute(Client client) {
        Client savedClient = clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ClientDto(savedClient));
    }

    public String createForm(Client client, Model model) {
        clientRepository.save(client);
        return "redirect:/clients/view";
    }
}
