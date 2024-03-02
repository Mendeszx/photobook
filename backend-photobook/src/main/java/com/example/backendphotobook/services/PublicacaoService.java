package com.example.backendphotobook.services;

import com.example.backendphotobook.dtos.request.DeletarPublicacaoRequest;
import com.example.backendphotobook.dtos.request.PublicacaoRequest;
import com.example.backendphotobook.dtos.response.DeletarPublicacaoResponse;
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

    @Autowired
    private ComentarioService comentarioService;

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
        PublicacoesEntity publicacoesEntity;
        try {
            publicacoesEntity = findById(publicacaoId);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

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

        return ResponseEntity.ok().body(listarPublicacoesResponse);
    }

    public ResponseEntity<DeletarPublicacaoResponse> deletarUmaPublicacao(DeletarPublicacaoRequest deletarPublicacaoRequest) {
        DeletarPublicacaoResponse deletarPublicacaoResponse;

        try {
            deletarPublicacao(deletarPublicacaoRequest);
            deletarPublicacaoResponse = deletarUmaPublicacaoResponse(200, HttpStatus.OK, "Publicação apagada com sucesso.");

        } catch (Exception e) {
            deletarPublicacaoResponse = deletarUmaPublicacaoResponse(400, HttpStatus.BAD_REQUEST, "Erro: " + e.getMessage());
        }

        return ResponseEntity.status(deletarPublicacaoResponse.getHttpStatusCode()).body(deletarPublicacaoResponse);
    }

    private DeletarPublicacaoResponse deletarUmaPublicacaoResponse(int httpStatusCode, HttpStatus httpStatus, String mensagem) {
        DeletarPublicacaoResponse deletarPublicacaoResponse = new DeletarPublicacaoResponse();

        deletarPublicacaoResponse.setHttpStatusCode(httpStatusCode);
        deletarPublicacaoResponse.setHttpStatus(httpStatus);
        deletarPublicacaoResponse.setMensagem(mensagem);

        return deletarPublicacaoResponse;
    }

    private void deletarPublicacao(DeletarPublicacaoRequest deletarPublicacaoRequest) {
        long publicacaoId = Long.parseLong(deletarPublicacaoRequest.getPublicacaoId());
        long usuarioId = Long.parseLong(deletarPublicacaoRequest.getUsuarioId());

        PublicacoesEntity publicacoesEntity = findById(publicacaoId);

        if (usuarioId == publicacoesEntity.getUsuarioId().getId()){
            comentarioService.deletarComentarioDeUmaPublicacao(publicacoesEntity);
            publicacoesRepository.delete(publicacoesEntity);
        } else {
            throw new RuntimeException("Usuário não autorizado a excluir essa publicação");
        }

    }

    public PublicacoesEntity findById(long publicacaoId) {
        Optional<PublicacoesEntity> publicacoesEntity = publicacoesRepository.findById(publicacaoId);

        if (publicacoesEntity.isPresent()) {
            return publicacoesEntity.get();
        } else {
            throw new RuntimeException("Publicação não encontrada");
        }
    }
}
