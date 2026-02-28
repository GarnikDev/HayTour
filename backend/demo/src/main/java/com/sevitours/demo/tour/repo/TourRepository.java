package com.sevitours.demo.tour.repo;

import com.sevitours.demo.tour.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TourRepository extends JpaRepository<Tour, UUID> {
}
