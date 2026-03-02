package com.sevitours.demo.tour_slot.repo;

import com.sevitours.demo.tour_slot.model.TourSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TourSlotRepo extends JpaRepository<TourSlot, UUID> {

}
