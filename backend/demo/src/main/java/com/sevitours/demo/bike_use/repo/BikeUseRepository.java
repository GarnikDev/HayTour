package com.sevitours.demo.bike_use.repo;

import com.sevitours.demo.bike_use.model.BikeUse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BikeUseRepository extends JpaRepository<BikeUse, UUID> {
}
