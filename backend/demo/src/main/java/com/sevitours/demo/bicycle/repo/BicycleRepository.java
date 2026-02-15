package com.sevitours.demo.bicycle.repo;

import com.sevitours.demo.bicycle.model.Bicycle;
import com.sevitours.demo.bicycle.enums.BikeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BicycleRepository extends JpaRepository<Bicycle, Long> {
    List<Bicycle> findAllByType(BikeType type);
}
