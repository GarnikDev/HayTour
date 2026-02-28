package com.sevitours.demo.rental.repo;

import com.sevitours.demo.rental.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface RentalRepository extends JpaRepository<Rental, UUID> {
}
