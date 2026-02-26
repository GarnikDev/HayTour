package com.sevitours.demo.bike_use.repo;

import com.sevitours.demo.bike_use.model.BikeUse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BikeUseRepository extends JpaRepository<BikeUse, UUID> {
}
