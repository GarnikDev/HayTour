package com.sevitours.demo.user.contoller;

import com.sevitours.demo.user.enums.AppUserRole;
import com.sevitours.demo.user.model.AppUser;
import com.sevitours.demo.user.model.LoginDto;
import com.sevitours.demo.user.model.LoginResponseDto;
import com.sevitours.demo.user.model.RegisterDto;
import com.sevitours.demo.user.repo.AppUserRepository;
import com.sevitours.demo.user.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AppUserController {

    private final AppUserRepository appUserRepository;
    private final AuthService authService;

    public AppUserController(AppUserRepository appUserRepository,
                             AuthService authService) {
        this.appUserRepository = appUserRepository;
        this.authService = authService;
    }
/*
    @GetMapping()
    public ResponseEntity<AppUser> (Model model) {

    }
*/



    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginDto loginDto,
            HttpServletResponse response   // ← use this instead of HttpSession
    ) {
        try {
            LoginResponseDto tokens = authService.login(loginDto);   // calls Supabase, gets real tokens

            // Set secure cookie with JWT
            ResponseCookie cookie = ResponseCookie.from("accessToken", tokens.getAccess_token())
                    .httpOnly(true)
                    .secure(false) //needs to be set true in production
                    .path("/")
                    .sameSite("Lax") //needs to be set Strict in production
                    .maxAge(3600)
                    .build();

            //response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            String auth = cookie.toString();

            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            return ResponseEntity.ok(Map.of(
                    "message", "Login successful",
                    "username", loginDto.getUsername()
            ));

        } catch (UsernameNotFoundException | BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            // Log the real error!
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Server error"));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication == null) {
            System.out.println("DEBUG: Authentication object is NULL");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Auth is null");
        }

        System.out.println("DEBUG: Principal type: " + authentication.getPrincipal().getClass().getName());

        if (!(authentication.getPrincipal() instanceof Jwt)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Principal is not JWT");
        }

        Jwt jwt = (Jwt) authentication.getPrincipal();
        UUID userId = UUID.fromString(jwt.getSubject());

        // Explicitly handling the Optional
        java.util.Optional<AppUser> userOptional = appUserRepository.findById(userId);

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "User not found in local database"));
        }
    }


    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@RequestBody RegisterDto registerDto) {
        // 1. Create user in Supabase and get the UUID
        UUID supabaseUuid = authService.createUser(registerDto.getEmail(), registerDto.getPassword());

        AppUser appUser = new AppUser();
        appUser.setId(supabaseUuid);
        appUser.setUsername(registerDto.getUsername());
        appUser.setPhone(registerDto.getPhone());
        appUser.setIdNumber(registerDto.getIdNumber());
        appUser.setEmail(registerDto.getEmail());
        appUser.setCreatedAt(OffsetDateTime.now());
        appUser.setEnabled(true);
        appUser.setRole(AppUserRole.CLIENT);

        AppUser newAppUser = appUserRepository.save(appUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAppUser);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        // Optional: Call Supabase logout API if needed (using refresh token or session)
        ResponseCookie cookie = ResponseCookie.from("accessToken", "")
                .httpOnly(true)
                .secure(false) // true in prod
                .path("/")
                .sameSite("Lax") // Strict in prod
                .maxAge(0) // Clears the cookie
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok(Map.of("message", "Logged out"));
    }
}
