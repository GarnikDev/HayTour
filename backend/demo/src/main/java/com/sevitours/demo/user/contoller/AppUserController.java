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

import java.time.OffsetDateTime;
import java.util.Map;

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

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = authentication.getName();

        AppUser user = appUserRepository.findByUsername(username);

        return ResponseEntity.ok(user);
    }

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
                    .secure(true)
                    .path("/")
                    .sameSite("Strict")
                    .maxAge(3600)
                    .build();

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


    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@RequestBody RegisterDto registerDto) {

        authService.createUser(registerDto.getEmail(), registerDto.getPassword());

        System.out.println("\n\n\n\nUsername: " + registerDto.getUsername()+"\n\n\n\n");

        AppUser appUser = new AppUser();

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
}
