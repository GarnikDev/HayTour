package com.sevitours.demo.tour_offer.repo;

import com.sevitours.demo.tour_offer.model.TourOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TourOfferRepo extends JpaRepository<TourOffer, UUID> {

}
