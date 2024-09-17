package com.Perseo_Platform.controllers;

import com.Perseo_Platform.dtos.request.LoginRequest;
import com.Perseo_Platform.dtos.request.RegisterRequest;
import com.Perseo_Platform.dtos.response.AuthResponse;
import com.Perseo_Platform.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {return ResponseEntity.ok(authService.login(request));}

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {return ResponseEntity.ok(authService.register(request));}
}
