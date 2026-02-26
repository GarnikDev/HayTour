package com.sevitours.demo.district.repo;

import com.sevitours.demo.district.enums.DistrictType;
import com.sevitours.demo.district.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DistrictRepository extends JpaRepository<District, UUID> {
    List<District> findAllByRegion(String region);
    List<District> findAllByType(DistrictType Type);
}
