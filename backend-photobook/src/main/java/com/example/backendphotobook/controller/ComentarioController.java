package com.example.backendphotobook.controller;

import com.example.backendphotobook.dtos.request.CadastrarComentarioRequest;
import com.example.backendphotobook.dtos.request.DeletarComentarioRequest;
import com.example.backendphotobook.dtos.request.ListarComentariosRequest;
import com.example.backendphotobook.dtos.response.*;
import com.example.backendphotobook.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping( "/novo-comentario")
    public ResponseEntity<CadastrarComentarioResponse> cadastrarNovoComentario(@RequestBody CadastrarComentarioRequest cadastrarComentarioRequest) {
        return comentarioService.cadastrarNovoComentario(cadastrarComentarioRequest);
    }

    @GetMapping("/listar-comentarios")
    public ResponseEntity<List<ListarComentariosResponse>> listarComentarios(@RequestBody ListarComentariosRequest listarComentariosRequest) {
        return comentarioService.listarComentarios(listarComentariosRequest);
    }

    @DeleteMapping("/deletar-comentario")
    public ResponseEntity<DeletarComentarioResponse> deletarUmComentario(@RequestBody DeletarComentarioRequest deletarComentarioRequest) {
        return comentarioService.deletarUmComentario(deletarComentarioRequest);
    }
}
