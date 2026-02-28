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
import java.util.UUID;

@Controller
@RequestMapping("/api/clients")
@CrossOrigin(origins = "http://localhost:3000/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer") // Will probably need to be improved in order to ensure the district with the id exists
    public ResponseEntity<CustomerDto> createClient(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @GetMapping("/view")
    public ResponseEntity<List<CustomerDto>> getAllClients(Model model) {
        Void input = null;
        return customerService.getAll(input);
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<CustomerDto> getClient(@PathVariable UUID id) {
        return customerService.getById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CustomerDto> updateClient(@PathVariable UUID id, @RequestBody Customer customer) {
        return customerService.update(new UpdateCustomerCommand(id, customer));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        return customerService.delete(id);
    }
}
