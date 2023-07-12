package com.example.educationalplatform.Auth;

import com.example.educationalplatform.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @GetMapping("/user")
    public ResponseEntity<User> getUserInfo() {
        System.out.println("first insiiide function of get user::");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("insiiide function of get user::");
        System.out.println(authentication);
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(user);


    }
}
