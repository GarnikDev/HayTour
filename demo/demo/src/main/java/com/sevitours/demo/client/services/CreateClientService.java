package com.sevitours.demo.client.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.client.model.Customer;
import com.sevitours.demo.client.model.CustomerDto;
import com.sevitours.demo.client.repo.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateClientService implements Command<Customer, CustomerDto> {

    private final CustomerRepository customerRepository;
    public CreateClientService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseEntity<CustomerDto> execute(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CustomerDto(savedCustomer));
    }
}
