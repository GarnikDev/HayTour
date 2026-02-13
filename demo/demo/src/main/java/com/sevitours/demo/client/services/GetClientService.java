package com.sevitours.demo.client.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.client.model.Customer;
import com.sevitours.demo.client.model.CustomerDto;
import com.sevitours.demo.client.model.CustomerMapper;
import com.sevitours.demo.client.repo.CustomerRepository;
import com.sevitours.demo.language.repo.LanguageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetClientService implements Query<Void, List<CustomerDto>> {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final LanguageRepository languageRepository;


    public GetClientService(CustomerRepository customerRepository,
                            CustomerMapper customerMapper,
                            LanguageRepository languageRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.languageRepository = languageRepository;
    }


    @Override
    public ResponseEntity<List<CustomerDto>> execute(Void input) { // returns All existing clients
        List<CustomerDto> customerDto = customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    public ResponseEntity<CustomerDto> execute(Integer id) { // Returns the client with inputed id
        Optional<Customer> client = customerRepository.findById(id);
        if (client.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(customerMapper.toDto(client.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
