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

    public void createUser(String email, String password) { // Changed to void, as you don't need full response
        String url = supabaseUrl + "/auth/v1/signup";
        HttpHeaders headers = createHeaders(anonKey);

        Map<String, Object> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);
        body.put("email_confirm", false);

        System.out.println("\n\n\n\n\nPassword: " + password + "\n\n\n\n\n");

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);

        if (response.getStatusCode() != HttpStatus.OK && response.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("User creation failed: " + response.getBody().get("error"));
        }
    }
}
