package com.example.backendphotobook.controller;

import com.example.backendphotobook.dtos.request.DeletarPublicacaoRequest;
import com.example.backendphotobook.dtos.request.PublicacaoRequest;
import com.example.backendphotobook.dtos.response.DeletarPublicacaoResponse;
import com.example.backendphotobook.dtos.response.ListarPublicacoesResponse;
import com.example.backendphotobook.dtos.response.PublicacaoResponse;
import com.example.backendphotobook.entities.PublicacoesEntity;
import com.example.backendphotobook.services.PublicacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/publicacao")
public class PublicacaoController {

    @Autowired
    private PublicacaoService publicacaoService;

    @PostMapping(path = "/nova-publicacao", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<PublicacaoResponse> cadastrarNovaPublicacao(@ModelAttribute PublicacaoRequest publicacaoRequest) {
        return publicacaoService.cadastrarNovaPublicacao(publicacaoRequest);
    }

    @GetMapping("/listar-publicacoes")
    public ResponseEntity<List<ListarPublicacoesResponse>> listarPublicacoes() {
        return publicacaoService.listarPublicacoes();
    }

    @GetMapping("/{publicacaoId}")
    public ResponseEntity<ListarPublicacoesResponse> procurarUmaPublicacao(@PathVariable long publicacaoId) {
        return publicacaoService.procurarUmaPublicacao(publicacaoId);
    }

    @DeleteMapping("/deletar-publicacao")
    public ResponseEntity<DeletarPublicacaoResponse> deletarPublicacao(@RequestBody DeletarPublicacaoRequest deletarPublicacaoRequest) {
        return publicacaoService.deletarUmaPublicacao(deletarPublicacaoRequest);
    }
}
