package com.sevitours.demo.client;

import com.sevitours.demo.client.services.CreateClientService;
import com.sevitours.demo.client.services.DeleteClientService;
import com.sevitours.demo.client.services.GetClientService;
import com.sevitours.demo.client.services.UpdateClientService;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<String> createClient(){
        return createClientService.execute(null);
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClients() {
        return getClientService.execute(null);
    }
}
