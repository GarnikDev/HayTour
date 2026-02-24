package com.sevitours.demo.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .logout(logout -> logout.disable())
                .cors(cors -> cors.configure(http))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        // Explicitly permit these first
                        .requestMatchers("/register", "/login", "/error", "/logout").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth -> oauth
                        .bearerTokenResolver(bearerTokenResolver()) // This looks for your cookie
                        .jwt(jwt -> jwt.decoder(jwtDecoder()))
                );

        return http.build();
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
