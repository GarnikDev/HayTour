package com.sevitours.demo.bill.repo;

import com.sevitours.demo.bill.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BillRepository extends JpaRepository<Bill, UUID> {

}
