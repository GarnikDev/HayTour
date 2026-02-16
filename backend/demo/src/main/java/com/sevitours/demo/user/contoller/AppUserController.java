package com.sevitours.demo.user.contoller;

import com.sevitours.demo.user.enums.AppUserRole;
import com.sevitours.demo.user.model.AppUser;
import com.sevitours.demo.user.repo.AppUserRepository;
import com.sevitours.demo.user.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
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
    public String login(@RequestParam String email, @RequestParam String password) {
        return authService.login(email, password);
    }

    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@RequestBody AppUser appUser) {

        authService.createUser(appUser.getEmail(), appUser.getPassword());

        appUser.setCreatedAt(OffsetDateTime.now());
        appUser.setEnabled(true);
        appUser.setRole(AppUserRole.CLIENT);
        AppUser newAppUser = appUserRepository.save(appUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAppUser);
    }
}
