package com.example.backendphotobook.controller;

import com.example.backendphotobook.dtos.request.CadastroUsuarioRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/health")
public class HealthCheck {

    @GetMapping("/check")
    public String healthCheck() {
        return "up";
    }
}
