package com.sevitours.demo.bicycle;

import org.springframework.http.ResponseEntity;

public interface Query <I, O>{
    ResponseEntity<String> execute(I input);
}
