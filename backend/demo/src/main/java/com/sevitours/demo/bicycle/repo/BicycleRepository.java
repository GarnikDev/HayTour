package com.sevitours.demo.bicycle.repo;

import com.sevitours.demo.bicycle.model.Bicycle;
import com.sevitours.demo.bicycle.enums.BikeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BicycleRepository extends JpaRepository<Bicycle, UUID> {
    List<Bicycle> findAllByType(BikeType type);
}
