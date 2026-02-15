package com.sevitours.demo.client.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.client.model.Customer;
import com.sevitours.demo.client.repo.CustomerRepository;
import com.sevitours.demo.common.ItemNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteClientService implements Command<Integer, Void>{

    private CustomerRepository customerRepository;

    public DeleteClientService(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer id) {
        Optional<Customer> optionalClient = customerRepository.findById(id);
        if (optionalClient.isPresent()) {
            customerRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        throw new ItemNotFound("Bill");
    }
}
