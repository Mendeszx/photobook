package com.example.backendphotobook.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/health")
public class HealthController {

    @GetMapping("/check")
    public String healthCheck() {
        return "up";
    }
}
