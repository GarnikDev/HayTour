package com.sevitours.demo.tour.repo;

import com.sevitours.demo.tour.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, Integer> {
}
