package com.sevitours.demo.client.controller;

import com.sevitours.demo.client.model.Customer;
import com.sevitours.demo.client.model.CustomerDto;
import com.sevitours.demo.client.repo.CustomerRepository;
import com.sevitours.demo.client.services.UpdateCustomerCommand;
import com.sevitours.demo.client.services.*;
import com.sevitours.demo.language.repo.LanguageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class CustomerController {
    private final GetClientService getClientService;
    private final CreateClientService createClientService;
    private final DeleteClientService deleteClientService;
    private final UpdateClientService updateClientService;
    private final LanguageRepository languageRepository;
    private final CustomerRepository customerRepository;

    public CustomerController(GetClientService getClientService,
                              CreateClientService createClientService,
                              DeleteClientService deleteClientService,
                              UpdateClientService updateClientService,
                              LanguageRepository languageRepository,
                              CustomerRepository customerRepository) {
        this.getClientService = getClientService;
        this.createClientService = createClientService;
        this.deleteClientService = deleteClientService;
        this.updateClientService = updateClientService;
        this.languageRepository = languageRepository;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/customer") // Will probably need to be improved in order to ensure the district with the id exists
    public ResponseEntity<CustomerDto> createClient(@RequestBody Customer customer) {
        return createClientService.execute(customer);
    }

    @GetMapping("/view")
    public ResponseEntity<List<CustomerDto>> getAllClients(Model model) {
        Void input = null;
        return getClientService.execute(input);
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<CustomerDto> getClient(@PathVariable Integer id) {
        return getClientService.execute(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CustomerDto> updateClient(@PathVariable Integer id, @RequestBody Customer customer) {
        getClientService.execute(id);
        return updateClientService.execute(new UpdateCustomerCommand(id, customer));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        return deleteClientService.execute(id);
    }
}
