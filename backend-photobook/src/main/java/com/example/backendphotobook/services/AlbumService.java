package com.example.backendphotobook.services;

import com.example.backendphotobook.dtos.request.CadastrarNovoAlbumRequest;
import com.example.backendphotobook.dtos.response.CadastrarNovoAlbumResponse;
import com.example.backendphotobook.dtos.response.ListarAlbunsResponse;
import com.example.backendphotobook.entities.AlbunsEntity;
import com.example.backendphotobook.entities.ImagensAlbunsEntity;
import com.example.backendphotobook.entities.UsuariosEntity;
import com.example.backendphotobook.repository.AlbunsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbunsRepository albunsRepository;
    @Autowired
    private UsuariosService usuariosService;
    @Autowired
    private ImagensAlbunsService imagensAlbunsService;

    public ResponseEntity<CadastrarNovoAlbumResponse> cadastrarNovoAlbum(CadastrarNovoAlbumRequest cadastrarNovoAlbumRequest) {
        CadastrarNovoAlbumResponse cadastrarComentarioResponse;

        try {
            salvarAlbum(cadastrarNovoAlbumRequest);
            cadastrarComentarioResponse = cadastrarNovoAlbumResponse(201, HttpStatus.CREATED, "Album criado com sucesso.");

        } catch (Exception e) {
            cadastrarComentarioResponse = cadastrarNovoAlbumResponse(400, HttpStatus.BAD_REQUEST, "Erro: " + e.getMessage());
        }

        return ResponseEntity.status(cadastrarComentarioResponse.getHttpStatusCode()).body(cadastrarComentarioResponse);
    }

    private CadastrarNovoAlbumResponse cadastrarNovoAlbumResponse(int httpStatusCode, HttpStatus httpStatus, String mensagem) {
        CadastrarNovoAlbumResponse cadastrarNovoAlbumResponse = new CadastrarNovoAlbumResponse();

        cadastrarNovoAlbumResponse.setHttpStatusCode(httpStatusCode);
        cadastrarNovoAlbumResponse.setHttpStatus(httpStatus);
        cadastrarNovoAlbumResponse.setMensagem(mensagem);

        return cadastrarNovoAlbumResponse;
    }

    private void salvarAlbum(CadastrarNovoAlbumRequest cadastrarNovoAlbumRequest) throws IOException {

        if (cadastrarNovoAlbumRequest.getImagens() == null || cadastrarNovoAlbumRequest.getImagens().length == 0) {
            throw new RuntimeException("O album não pode estar vazio");
        }

        AlbunsEntity albunsEntity = new AlbunsEntity();
        BeanUtils.copyProperties(cadastrarNovoAlbumRequest, albunsEntity);

        UsuariosEntity usuariosEntity = usuariosService.findById(Long.parseLong(cadastrarNovoAlbumRequest.getUsuarioId()));

        Date dataDeCadastro = new Date();

        albunsEntity.setDataDeCadastro(dataDeCadastro);
        albunsEntity.setUsuarioId(usuariosEntity);

        albunsEntity = albunsRepository.save(albunsEntity);

        for (MultipartFile imagem : cadastrarNovoAlbumRequest.getImagens()) {

            ImagensAlbunsEntity imagensAlbunsEntity = new ImagensAlbunsEntity();

            imagensAlbunsEntity.setAlbumId(albunsEntity);
            imagensAlbunsEntity.setImagem(imagem.getBytes());

            imagensAlbunsService.save(imagensAlbunsEntity);
        }
    }

    public ResponseEntity<List<ListarAlbunsResponse>> listarAlbuns(long usuarioId) {
        List<ListarAlbunsResponse> albunsResponseList = new ArrayList<>();
        UsuariosEntity usuariosEntity = usuariosService.findById(usuarioId);
        List<AlbunsEntity> albunsEntityList = albunsRepository.findByUsuarioIdOrderByDataDeCadastroDesc(usuariosEntity);

        for (AlbunsEntity albunsEntity : albunsEntityList) {
            ListarAlbunsResponse listarAlbunsResponse = new ListarAlbunsResponse();

            listarAlbunsResponse.setImagens(imagensAlbunsService.findByAlbumId(albunsEntity));
            listarAlbunsResponse.setDescricao(albunsEntity.getDescricao());
            listarAlbunsResponse.setCurtidas(albunsEntity.getCurtidas());
            listarAlbunsResponse.setNome(albunsEntity.getUsuarioId().getNome());
            listarAlbunsResponse.setDataDeCadastro(albunsEntity.getDataDeCadastro());

            albunsResponseList.add(listarAlbunsResponse);
        }

        return ResponseEntity.ok().body(albunsResponseList);
    }
}
