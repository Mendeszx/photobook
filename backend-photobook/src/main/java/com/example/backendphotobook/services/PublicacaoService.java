package com.example.backendphotobook.services;

import com.example.backendphotobook.dtos.request.PublicacaoRequest;
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

        if (!publicacaoRequest.getFoto().isEmpty() || publicacaoRequest.getFoto() != null){
            publicacoesEntity.setFoto(publicacaoRequest.getFoto().getBytes());
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
}
