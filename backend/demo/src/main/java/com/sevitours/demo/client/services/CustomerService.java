package com.sevitours.demo.client.services;

import com.sevitours.demo.client.model.Customer;
import com.sevitours.demo.client.model.CustomerDto;
import com.sevitours.demo.client.model.CustomerMapper;
import com.sevitours.demo.client.repo.CustomerRepository;
import com.sevitours.demo.common.ItemNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerMapper customerMapper,
                           CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<CustomerDto> create(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CustomerDto(savedCustomer));
    }

    public ResponseEntity<Void> delete(UUID id) {
        Optional<Customer> optionalClient = customerRepository.findById(id);
        if (optionalClient.isPresent()) {
            customerRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        throw new ItemNotFound("Customer");
    }

    public ResponseEntity<List<CustomerDto>> getAll(Void input) { // returns All existing clients
        List<CustomerDto> customerDto = customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    public ResponseEntity<CustomerDto> getById(UUID id) { // Returns the client with inputed id
        Optional<Customer> client = customerRepository.findById(id);
        if (client.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(customerMapper.toDto(client.get()));
        } else {
            throw new ItemNotFound("Client");
        }
    }

    public ResponseEntity<CustomerDto> update(UpdateCustomerCommand command) {
        Optional<Customer> optionalClient = customerRepository.findById(command.getId());
        if(optionalClient.isPresent()) {
            Customer customer = command.getCustomer();
            customer.setId(command.getId());
            customerRepository.save(customer);
            return ResponseEntity.ok(new CustomerDto(customer));
        }
        throw new ItemNotFound("Bill");
    }
}
