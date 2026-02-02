package com.sevitours.demo.bill.service;

import com.sevitours.demo.bicycle.BicycleMapper;
import com.sevitours.demo.bicycle.BicycleRepository;

public class BillService{

    private final BicycleRepository bicycleRepository;
    private final BicycleMapper bicycleMapper;

    public BillService(BicycleRepository bicycleRepository, BicycleMapper bicycleMapper) {
        this.bicycleRepository = bicycleRepository;
        this.bicycleMapper = bicycleMapper;
    }


}
