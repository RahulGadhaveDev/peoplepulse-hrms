package com.hrms.controller;

import com.hrms.dto.AuthRequest;
import com.hrms.dto.AuthResponse;
import com.hrms.service.AuthService;
import com.hrms.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:5173/")
public class AuthConntroller {

    private final AuthService authService ;

    public AuthConntroller(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
