package com.sevitours.demo.client.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.client.Client;
import com.sevitours.demo.client.ClientDto;
import com.sevitours.demo.client.ClientMapper;
import com.sevitours.demo.client.ClientRepository;
import com.sevitours.demo.language.Language;
import com.sevitours.demo.language.LanguageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

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

    //UI List
    public String listClients(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
        return "client-list";
    }


    //testing method

    public String formulario(Model model) { // returns All existing clients
        model.addAttribute("client", new Client());
        return "client-form.html";
    }


    public ResponseEntity<ClientDto> execute(Integer id) { // Returns the client with inputed id
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(clientMapper.toDto(client.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<List<ClientDto>> execute(String input) {// Returns all the clients with inputed name
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
