package com.example.backendphotobook.controller;

import com.example.backendphotobook.dtos.request.CadastroUsuarioRequest;
import com.example.backendphotobook.dtos.response.CadastroUsuarioResponse;
import com.example.backendphotobook.services.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cadastro")
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;

    @PostMapping("/usuario")
    public ResponseEntity<CadastroUsuarioResponse> cadastrarNovoUsuario(@RequestBody CadastroUsuarioRequest cadastroUsuarioRequest) {
        return cadastroService.cadastrarNovoUsuario(cadastroUsuarioRequest);
    }
}
