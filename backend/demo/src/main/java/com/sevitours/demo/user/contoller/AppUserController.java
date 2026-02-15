package com.sevitours.demo.user.contoller;

import com.sevitours.demo.user.model.AppUser;
import com.sevitours.demo.user.repo.AppUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:3000/")
public class AppUserController {

    private final AppUserRepository appUserRepository;

    public AppUserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }
/*
    @GetMapping()
    public ResponseEntity<AppUser> (Model model) {

    }
*/
}
