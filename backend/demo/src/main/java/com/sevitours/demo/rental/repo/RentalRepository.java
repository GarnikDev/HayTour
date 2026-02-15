package com.sevitours.demo.rental.repo;

import com.sevitours.demo.rental.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
}
