package com.sevitours.demo.user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final String anonKey;
    private final String serviceRoleKey;
    private final String supabaseUrl;
    private final RestTemplate restTemplate = new RestTemplate();
    public AuthService(@Value("${supabase.anon-key}") String anonKey,
                       @Value("${supabase.service-key}") String serviceRoleKey,
                       @Value("${supabase.url}") String supabaseUrl) {
        this.anonKey = anonKey;
        this.serviceRoleKey = serviceRoleKey;
        this.supabaseUrl = supabaseUrl;
    }

    public String login(String email, String password) {

        String url = supabaseUrl + "/auth/v1/token?grant_type=password";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("apikey", anonKey);

        String body = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response =
                restTemplate.exchange(url, HttpMethod.POST, request, Map.class);

        Map<String, Object> responseBody = response.getBody();

        if (responseBody != null && responseBody.containsKey("access_token")) {
            return (String) responseBody.get("access_token"); // this is the JWT
        } else {
            throw new RuntimeException("Login failed: " + responseBody);
        }
    }

    public ResponseEntity<Map> createUser(String email, String password) {

        String url = supabaseUrl + "/auth/v1/admin/users";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("apikey", serviceRoleKey);
        headers.setBearerAuth(serviceRoleKey);

        Map<String, Object> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);
        body.put("email_confirm", true);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response =
                restTemplate.exchange(url, HttpMethod.POST, request, Map.class);

        return response;
    }
}
