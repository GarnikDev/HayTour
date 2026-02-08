package com.sevitours.demo.client;

import com.sevitours.demo.client.services.*;
import com.sevitours.demo.language.Language;
import com.sevitours.demo.language.LanguageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {
    private final GetClientService getClientService;
    private final CreateClientService createClientService;
    private final DeleteClientService deleteClientService;
    private final UpdateClientService updateClientService;
    private final LanguageRepository languageRepository;

    public ClientController(GetClientService getClientService,
                            CreateClientService createClientService,
                            DeleteClientService deleteClientService,
                            UpdateClientService updateClientService,
                            LanguageRepository languageRepository) {
        this.getClientService = getClientService;
        this.createClientService = createClientService;
        this.deleteClientService = deleteClientService;
        this.updateClientService = updateClientService;
        this.languageRepository = languageRepository;
    }

    @PostMapping("/client") // Will probably need to be improved in order to ensure the district with the id exists
    public ResponseEntity<ClientDto> createClient(@RequestBody Client client) {
        return createClientService.execute(client);
    }

    @PostMapping("/client-form")
    public String createClientForm(@ModelAttribute("client") Client client, Model model) {
        return createClientService.createForm(client, model);
    }

    @ModelAttribute("languages")
    public List<Language> languages() {
        List<Language> languages = languageRepository.findAll();
        return  languages;
    }

    @GetMapping("/client-form")
    public String showClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "client-form";
    }


    @GetMapping("/view/form")
    public String viewForm(Model model) {
        return getClientService.formulario(model);
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
