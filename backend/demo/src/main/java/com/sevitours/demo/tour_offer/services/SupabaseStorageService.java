package com.sevitours.demo.tour_offer.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class SupabaseStorageService {


    private final String supabaseUrl;
    private final String serviceKey;

    public SupabaseStorageService(@Value("${security.url}") String supabaseUrl,
                                  @Value("${security.service-key}")String serviceKey) {
        this.supabaseUrl = supabaseUrl;
        this.serviceKey = serviceKey;
    }

    private final RestTemplate restTemplate = new RestTemplate();

    public String uploadFile(MultipartFile file) throws IOException {
        // 1. Sanitize the key (removes hidden newlines or spaces)
        String sanitizedKey = serviceKey.trim();

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String uploadUrl = supabaseUrl + "/storage/v1/object/TourCoverImages/" + fileName;

        HttpHeaders headers = new HttpHeaders();

        // 2. Set BOTH Authorization and apikey (Supabase often needs both for Storage)
        headers.setBearerAuth(sanitizedKey);
        headers.set("apikey", sanitizedKey);

        headers.setContentType(MediaType.parseMediaType(file.getContentType()));

        HttpEntity<byte[]> request = new HttpEntity<>(file.getBytes(), headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    uploadUrl,
                    HttpMethod.POST,
                    request,
                    String.class
            );

            // This is the public URL format for Supabase
            return supabaseUrl + "/storage/v1/object/public/TourCoverImages/" + fileName;

        } catch (org.springframework.web.client.HttpClientErrorException e) {
            System.err.println("Supabase Error Body: " + e.getResponseBodyAsString());
            throw e;
        }
    }

    public void deleteFile(String imageUrl) {
        if (imageUrl == null || imageUrl.isBlank()) return;

        try {
            // 1. Extract the filename from the end of the URL
            String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);

            // 2. Construct the internal storage URL (targeting the specific bucket)
            String deleteUrl = supabaseUrl + "/storage/v1/object/TourCoverImages/" + fileName;

            // 3. Set up the same headers used for uploading
            String sanitizedKey = serviceKey.trim();
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(sanitizedKey);
            headers.set("apikey", sanitizedKey);

            HttpEntity<Void> request = new HttpEntity<>(headers);

            // 4. Execute the DELETE request
            ResponseEntity<String> response = restTemplate.exchange(
                    deleteUrl,
                    HttpMethod.DELETE,
                    request,
                    String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Successfully deleted file from Supabase: " + fileName);
            }

        } catch (org.springframework.web.client.HttpClientErrorException e) {
            System.err.println("Supabase Delete Error: " + e.getResponseBodyAsString());
            // We don't necessarily want to crash the whole update if deletion fails,
            // but we should log it.
        } catch (Exception e) {
            System.err.println("Failed to delete file from Supabase: " + e.getMessage());
        }
    }

}
