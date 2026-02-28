package com.sevitours.demo.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import jakarta.servlet.http.Cookie;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .logout(logout -> logout.disable())
                // 1. Tell Spring to use our custom CORS bean below
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        // 2. Explicitly permit ALL OPTIONS (preflight) requests
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // Explicitly permit these
                        .requestMatchers("/register", "/login", "/error", "/logout", "/api/tourOffers/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth -> oauth
                        .bearerTokenResolver(bearerTokenResolver()) // This looks for your cookie
                        .jwt(jwt -> jwt.decoder(jwtDecoder()))
                );

        return http.build();
    }

    // 3. Define the Global CORS rules
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Allow BOTH your CRM (3000) and ERP (3001) frontends
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:3001"));

        // Allow all standard methods, especially OPTIONS for preflight
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

        // Allow all headers
        configuration.setAllowedHeaders(List.of("*"));

        // CRITICAL FOR COOKIES: You must allow credentials to pass the JWT cookie
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        String jwkSetUri = "https://ylqglvrlwoltakrnsxym.supabase.co/auth/v1/.well-known/jwks.json";

        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri)
                .jwsAlgorithm(SignatureAlgorithm.ES256)
                .build();

        // your existing validators stay exactly the same
        OAuth2TokenValidator<Jwt> audienceValidator =
                new JwtClaimValidator<List<String>>(
                        JwtClaimNames.AUD,
                        aud -> aud != null && aud.contains("authenticated"));

        OAuth2TokenValidator<Jwt> issuerValidator = new JwtIssuerValidator(
                "https://ylqglvrlwoltakrnsxym.supabase.co/auth/v1");

        OAuth2TokenValidator<Jwt> combinedValidator = new DelegatingOAuth2TokenValidator<>(
                new JwtTimestampValidator(java.time.Duration.ofSeconds(60)),
                audienceValidator,
                issuerValidator
        );

        jwtDecoder.setJwtValidator(combinedValidator);

        return jwtDecoder;
    }

    @Bean
    public BearerTokenResolver bearerTokenResolver() {
        DefaultBearerTokenResolver defaultResolver = new DefaultBearerTokenResolver();

        return request -> {

            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if ("accessToken".equals(cookie.getName())) {
                        System.out.println("TOKEN FROM COOKIE: " + cookie.getValue());
                        return cookie.getValue();
                    }
                }
            }

            String headerToken = defaultResolver.resolve(request);

            System.out.println("TOKEN FROM HEADER: " + headerToken);

            return headerToken;
        };
    }

    @Bean
    public org.springframework.context.ApplicationListener<org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent> failureListener() {
        return event -> {
            System.err.println("DEBUG AUTH FAILURE: " + event.getException().getMessage());
            if (event.getException().getCause() != null) {
                System.err.println("CAUSE: " + event.getException().getCause().getMessage());
            }
        };
    }
}