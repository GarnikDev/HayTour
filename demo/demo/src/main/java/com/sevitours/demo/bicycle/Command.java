package com.sevitours.demo.bicycle;

import org.springframework.http.ResponseEntity;

public interface Command <I, O>{
    ResponseEntity<String> execute(I input);
}
