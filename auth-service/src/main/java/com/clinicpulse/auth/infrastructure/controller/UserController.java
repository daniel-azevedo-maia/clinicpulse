package com.clinicpulse.auth.infrastructure.controller;

import com.clinicpulse.auth.domain.model.AppUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/me")
    public AppUser getCurrentUser(@AuthenticationPrincipal AppUser user) {
        return user;
    }
}
