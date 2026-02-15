package com.sevitours.demo.bill.service;

import com.sevitours.demo.Query;
import com.sevitours.demo.bill.model.BillDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BillQueryService implements Query<Void, List<BillDto>> {

    @Override
    public ResponseEntity<List<BillDto>> execute(Void input) {
        return null;
    }
}
