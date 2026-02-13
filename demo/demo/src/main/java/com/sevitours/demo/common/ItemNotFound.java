package com.sevitours.demo.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFound extends RuntimeException{
    public ItemNotFound(String itemName){
        super(itemName + " not found");
    }
}
