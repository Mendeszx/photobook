package com.example.backendphotobook.services;

import com.example.backendphotobook.dtos.request.PublicacaoRequest;
import com.example.backendphotobook.dtos.response.ListarPublicacoesResponse;
import com.example.backendphotobook.dtos.response.PublicacaoResponse;
import com.example.backendphotobook.entities.PublicacoesEntity;
import com.example.backendphotobook.entities.UsuariosEntity;
import com.example.backendphotobook.repository.PublicacoesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class PublicacaoService {

    @Autowired
    private PublicacoesRepository publicacoesRepository;

    @Autowired
    private UsuariosService usuariosService;

    public ResponseEntity<PublicacaoResponse> cadastrarNovaPublicacao(PublicacaoRequest publicacaoRequest) {
        PublicacaoResponse publicacaoResponse;

        try {
            salvarPublicacao(publicacaoRequest);
            publicacaoResponse = cadastrarNovaPublicacaoResponse(201, HttpStatus.CREATED, "Publicação enviada com sucesso.");

        } catch (Exception e) {
            publicacaoResponse = cadastrarNovaPublicacaoResponse(400, HttpStatus.BAD_REQUEST, "Erro: " + e.getMessage());
        }

        return ResponseEntity.status(publicacaoResponse.getHttpStatusCode()).body(publicacaoResponse);
    }

    private void salvarPublicacao(PublicacaoRequest publicacaoRequest) throws IOException {

        PublicacoesEntity publicacoesEntity = new PublicacoesEntity();
        BeanUtils.copyProperties(publicacaoRequest, publicacoesEntity);

        UsuariosEntity usuariosEntity = usuariosService.findById(publicacaoRequest.getUsuarioId());

        LocalDate dataDeCadastro = LocalDate.now();

        if (!publicacaoRequest.getFoto().isEmpty() || publicacaoRequest.getFoto() != null) {
            publicacoesEntity.setImagem(publicacaoRequest.getFoto().getBytes());
        }

        publicacoesEntity.setDataDeCadastro(dataDeCadastro);
        publicacoesEntity.setUsuarioId(usuariosEntity);

        publicacoesRepository.save(publicacoesEntity);
    }

    private PublicacaoResponse cadastrarNovaPublicacaoResponse(int httpStatusCode, HttpStatus httpStatus, String mensagem) {
        PublicacaoResponse publicacaoResponse = new PublicacaoResponse();

        publicacaoResponse.setHttpStatusCode(httpStatusCode);
        publicacaoResponse.setHttpStatus(httpStatus);
        publicacaoResponse.setMensagem(mensagem);

        return publicacaoResponse;
    }

    public ResponseEntity<List<ListarPublicacoesResponse>> listarPublicacoes() {
        List<ListarPublicacoesResponse> publicacoesResponseList = new ArrayList<>();
        List<PublicacoesEntity> publicacoesEntityList = publicacoesRepository.findAll();

        for (PublicacoesEntity publicacoesEntity : publicacoesEntityList) {
            ListarPublicacoesResponse listarPublicacoesResponse = new ListarPublicacoesResponse();

            if (publicacoesEntity.getImagem() != null) {
                listarPublicacoesResponse.setImagem(Base64.getEncoder().encodeToString(publicacoesEntity.getImagem()));
            }
            if (publicacoesEntity.getUsuarioId().getFoto() != null) {
                listarPublicacoesResponse.setFoto(Base64.getEncoder().encodeToString(publicacoesEntity.getUsuarioId().getFoto()));
            }

            listarPublicacoesResponse.setDescricao(publicacoesEntity.getDescricao());
            listarPublicacoesResponse.setCurtidas(publicacoesEntity.getCurtidas());
            listarPublicacoesResponse.setNome(publicacoesEntity.getUsuarioId().getNome());
            listarPublicacoesResponse.setDataDeCadastro(publicacoesEntity.getDataDeCadastro());

            publicacoesResponseList.add(listarPublicacoesResponse);
        }

        return ResponseEntity.ok().body(publicacoesResponseList);
    }

    public ResponseEntity<ListarPublicacoesResponse> procurarUmaPublicacao(long publicacaoId) {
        Optional<PublicacoesEntity> publicacoesEntity = publicacoesRepository.findById(publicacaoId);

        if (publicacoesEntity.isPresent()) {

            ListarPublicacoesResponse listarPublicacoesResponse = new ListarPublicacoesResponse();

            if (publicacoesEntity.get().getImagem() != null) {
                listarPublicacoesResponse.setImagem(Base64.getEncoder().encodeToString(publicacoesEntity.get().getImagem()));
            }
            if (publicacoesEntity.get().getUsuarioId().getFoto() != null) {
                listarPublicacoesResponse.setFoto(Base64.getEncoder().encodeToString(publicacoesEntity.get().getUsuarioId().getFoto()));
            }

            listarPublicacoesResponse.setDescricao(publicacoesEntity.get().getDescricao());
            listarPublicacoesResponse.setCurtidas(publicacoesEntity.get().getCurtidas());
            listarPublicacoesResponse.setNome(publicacoesEntity.get().getUsuarioId().getNome());
            listarPublicacoesResponse.setDataDeCadastro(publicacoesEntity.get().getDataDeCadastro());

            return ResponseEntity.ok().body(listarPublicacoesResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
