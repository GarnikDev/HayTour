package com.sevitours.demo.rental_bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface RentalBillRepository extends JpaRepository<RentalBill, Integer> {
}
