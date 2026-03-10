package com.sevitours.demo.tour_price.repo;

import com.sevitours.demo.tour_price.model.TourPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TourPriceRepo extends JpaRepository<TourPrice, UUID> {
}
