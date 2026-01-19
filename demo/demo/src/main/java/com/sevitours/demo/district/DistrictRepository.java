package com.sevitours.demo.district;

import com.sevitours.demo.common.enums.DistrictType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    List<District> findAllByRegion(String region);
    List<District> findAllByType(DistrictType Type);
}
