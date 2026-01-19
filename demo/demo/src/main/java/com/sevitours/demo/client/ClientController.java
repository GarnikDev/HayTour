package com.sevitours.demo.client;

import com.sevitours.demo.client.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final GetClientService getClientService;
    private final CreateClientService createClientService;
    private final DeleteClientService deleteClientService;
    private final UpdateClientService updateClientService;

    public ClientController(GetClientService getClientService,
                            CreateClientService createClientService,
                            DeleteClientService deleteClientService,
                            UpdateClientService updateClientService) {
        this.getClientService = getClientService;
        this.createClientService = createClientService;
        this.deleteClientService = deleteClientService;
        this.updateClientService = updateClientService;
    }

    @PostMapping("/client") // Will probably need to be improved in order to ensure the district with the id exists
    public ResponseEntity<ClientDto> createClient(@RequestBody Client client) {
        return createClientService.execute(client);
    }

    @GetMapping("/view")
    public ResponseEntity<List<ClientDto>> getAllClients(Model model) {
        Void input = null;
        return getClientService.execute(input);
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<ClientDto> getClient(@PathVariable Integer id) {
        return getClientService.execute(id);
    }

    @GetMapping("/view/name/{name}")
    public ResponseEntity<List<ClientDto>> getClientByName(@PathVariable String name) {
        return getClientService.execute(name);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Integer id, @RequestBody Client client) {
        getClientService.execute(id);
        return updateClientService.execute(new UpdateClientCommand(id, client));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        return deleteClientService.execute(id);
    }
}
