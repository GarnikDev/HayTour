package com.sevitours.demo.bicycle;

import com.sevitours.demo.common.enums.BikeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BicycleRepository extends JpaRepository<Bicycle, Long> {
    List<Bicycle> findAllByType(BikeType type);
}
