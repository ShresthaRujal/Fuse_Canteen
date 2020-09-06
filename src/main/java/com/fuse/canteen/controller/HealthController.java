package com.fuse.canteen.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("api/health")
@RequiredArgsConstructor
public class HealthController {

    private final PasswordEncoder passwordEncoder;
    @GetMapping
    public ResponseEntity<?> health(){
        return ResponseEntity.ok("Running .............. ");
    }

}
