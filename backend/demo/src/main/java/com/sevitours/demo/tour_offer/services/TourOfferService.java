package com.sevitours.demo.tour_offer.services;

import com.sevitours.demo.common.ItemNotFound;
import com.sevitours.demo.tour_offer.model.TourOffer;
import com.sevitours.demo.tour_offer.model.TourOfferDto;
import com.sevitours.demo.tour_offer.model.TourOfferMapper;
import com.sevitours.demo.tour_offer.repo.TourOfferRepo;
import com.sevitours.demo.user.model.AppUser;
import com.sevitours.demo.user.repo.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TourOfferService {

    private final AppUserRepository appUserRepository;
    private final TourOfferRepo tourOfferRepo;
    private final TourOfferMapper tourOfferMapper;
    private final SupabaseStorageService supabaseStorageService;

    public TourOfferService(TourOfferRepo tourOfferRepo,
                            TourOfferMapper tourOfferMapper,
                            SupabaseStorageService supabaseStorageService,
                            AppUserRepository appUserRepository) {
        this.tourOfferRepo = tourOfferRepo;
        this.tourOfferMapper = tourOfferMapper;
        this.supabaseStorageService = supabaseStorageService;
        this.appUserRepository = appUserRepository;
    }

    public ResponseEntity<TourOfferDto> create(TourOfferDto tourOfferDto, MultipartFile image) {
        try {
            TourOffer tourOffer = tourOfferMapper.toEntity(tourOfferDto);

            AppUser user = appUserRepository.findById(tourOfferDto.getAppUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + tourOfferDto.getAppUserId()));

            tourOffer.setAppUser(user);
            tourOffer.setCreatedAt(OffsetDateTime.now());

            String imageUrl = supabaseStorageService.uploadFile(image);
            tourOffer.setImageUrl(imageUrl);

            TourOffer savedTourOffer = tourOfferRepo.save(tourOffer);
            return ResponseEntity.status(HttpStatus.CREATED).body(tourOfferMapper.toDto(savedTourOffer));

        } catch (Exception e) {
            System.err.println("UPLOAD ERROR: " + e.getMessage());
            throw new RuntimeException("Tour creation failed: " + e.getMessage());
        }
    }

    public ResponseEntity<TourOfferDto> update(UUID id, TourOfferDto dto, MultipartFile image) {
        // 1. Find the existing tour or throw error
        TourOffer existingTour = tourOfferRepo.findById(id)
                .orElseThrow(() -> new ItemNotFound("Tour Offer"));

        // 2. Update the text/numeric fields from the DTO
        existingTour.setTitle(dto.getTitle());
        existingTour.setDescription(dto.getDescription());
        existingTour.setPlace(dto.getPlace());
        existingTour.setDuration(dto.getDuration());
        existingTour.setBasePrice(dto.getBasePrice());
        existingTour.setType(dto.getType());
        // Note: We usually don't update createdAt or the AppUser on an 'Edit'
        // unless you specifically want to track "lastModifiedBy"

        // 3. Handle the Image Swap
        if (image != null && !image.isEmpty()) {
            try {
                // Delete the OLD photo from Supabase storage to save space
                if (existingTour.getImageUrl() != null && !existingTour.getImageUrl().isEmpty()) {
                    supabaseStorageService.deleteFile(existingTour.getImageUrl());
                }

                // Upload the NEW photo and get the new URL
                String newImageUrl = supabaseStorageService.uploadFile(image);
                existingTour.setImageUrl(newImageUrl);
            } catch (Exception e) {
                throw new RuntimeException("Failed to process image update: " + e.getMessage());
            }
        }

        // 4. Save the updated entity
        TourOffer updated = tourOfferRepo.save(existingTour);

        // 5. Map back to DTO and return
        return ResponseEntity.ok(tourOfferMapper.toDto(updated));
    }

    public ResponseEntity<Void> delete(UUID id) {
        TourOffer tourOffer = tourOfferRepo.findById(id)
                .orElseThrow(() -> new ItemNotFound("Tour Offer"));

        // 1. Delete from Supabase first
        if (tourOffer.getImageUrl() != null) {
            supabaseStorageService.deleteFile(tourOffer.getImageUrl());
        }

        // 2. Delete from DB
        tourOfferRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity<List<TourOfferDto>> findAll() {
        List<TourOfferDto> tourOfferDtos = tourOfferRepo.findAll()
                .stream()
                .map(tourOfferMapper::toDto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(tourOfferDtos);
    }

    public ResponseEntity<TourOfferDto> findById(UUID id) {
        Optional<TourOffer> optional = tourOfferRepo.findById(id);
        if(optional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(tourOfferMapper.toDto(optional.get()));
        }
        throw new ItemNotFound("Tour Offer");
    }
}
