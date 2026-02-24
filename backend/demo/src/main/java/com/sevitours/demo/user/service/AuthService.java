package com.sevitours.demo.user.service;

import com.sevitours.demo.user.model.AppUser;
import com.sevitours.demo.user.model.LoginDto;
import com.sevitours.demo.user.model.LoginResponseDto;
import com.sevitours.demo.user.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthService {

    private final String anonKey;
    private final String supabaseUrl;
    private final RestTemplate restTemplate = new RestTemplate();
    private final AppUserRepository appUserRepository;
    public AuthService(@Value("${security.anon-key}") String anonKey,
                       @Value("${security.url}") String supabaseUrl,
                       AppUserRepository appUserRepository) {
        this.anonKey = anonKey;
        this.supabaseUrl = supabaseUrl;
        this.appUserRepository = appUserRepository;
    }


    private HttpHeaders createHeaders(String key) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("apikey", key);
        headers.set("Authorization", "Bearer " + key); // Supabase often requires this too
        return headers;
    }

    public LoginResponseDto login(LoginDto request) {
        AppUser appUser = appUserRepository.findByUsername(request.getUsername());
        if (appUser == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        String email = appUser.getEmail();

        Map<String, String> body = Map.of("email", email, "password", request.getPassword());
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, createHeaders(anonKey));
        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    supabaseUrl + "/auth/v1/token?grant_type=password",
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            Map<String, Object> respBody = response.getBody();
            String accessToken = (String) respBody.get("access_token");
            String refreshToken = (String) respBody.get("refresh_token");
            return new LoginResponseDto(accessToken, refreshToken);

        } catch (HttpClientErrorException e) {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    public UUID createUser(String email, String password) {
        String url = supabaseUrl + "/auth/v1/signup";

        // 1. Prepare the request body
        Map<String, Object> requestBody = Map.of(
                "email", email,
                "password", password
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, createHeaders(anonKey));

        try {
            // 2. Make the call to Supabase
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);
            Map<String, Object> body = response.getBody();

            if (body == null) {
                throw new RuntimeException("Supabase returned an empty response body.");
            }

            // 3. Handle the nested "user" object structure
            String idString = null;
            if (body.get("user") instanceof Map) {
                Map<String, Object> userMap = (Map<String, Object>) body.get("user");
                idString = (String) userMap.get("id");
            } else if (body.containsKey("id")) {
                // Fallback for flat structures
                idString = (String) body.get("id");
            }

            // 4. Validate the ID and return
            if (idString == null) {
                System.err.println("Unexpected Supabase Response: " + body);
                throw new RuntimeException("User created, but no ID found in response.");
            }

            return UUID.fromString(idString);

        } catch (HttpClientErrorException e) {
            // This catches 4xx errors (like "User already exists" or "Weak password")
            System.err.println("Supabase Auth Error: " + e.getResponseBodyAsString());
            throw new RuntimeException("Signup failed: " + e.getResponseBodyAsString());
        }
    }
}
