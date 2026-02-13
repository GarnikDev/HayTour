package com.sevitours.demo.client.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.client.model.Customer;
import com.sevitours.demo.client.model.CustomerDto;
import com.sevitours.demo.client.repo.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateClientService implements Command<UpdateCustomerCommand, CustomerDto> {

    private final CustomerRepository customerRepository;

    public UpdateClientService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseEntity<CustomerDto> execute(UpdateCustomerCommand command) {
        Optional<Customer> optionalClient = customerRepository.findById(command.getId());
        if(optionalClient.isPresent()) {
            Customer customer = command.getCustomer();
            customer.setId(command.getId());
            customerRepository.save(customer);
            return ResponseEntity.ok(new CustomerDto(customer));
        }
        return null;
        //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
