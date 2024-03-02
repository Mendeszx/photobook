package com.example.backendphotobook.controller;

import com.example.backendphotobook.dtos.request.CadastrarNovoAlbumRequest;
import com.example.backendphotobook.dtos.request.ListarAlbunsRequest;
import com.example.backendphotobook.dtos.response.CadastrarNovoAlbumResponse;
import com.example.backendphotobook.dtos.response.ListarAlbunsResponse;
import com.example.backendphotobook.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping( path = "/novo-album", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<CadastrarNovoAlbumResponse> cadastrarNovoAlbum(@ModelAttribute CadastrarNovoAlbumRequest cadastrarNovoAlbumRequest) {
        return albumService.cadastrarNovoAlbum(cadastrarNovoAlbumRequest);
    }

    @GetMapping("/listar-albuns/{usuarioId}")
    public ResponseEntity<List<ListarAlbunsResponse>> listarAlbuns(@PathVariable long usuarioId) {
        return albumService.listarAlbuns(usuarioId);
    }
}
