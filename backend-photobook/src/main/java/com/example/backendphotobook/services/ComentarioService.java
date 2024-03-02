package com.example.backendphotobook.services;

import com.example.backendphotobook.dtos.request.CadastrarComentarioRequest;
import com.example.backendphotobook.dtos.request.DeletarComentarioRequest;
import com.example.backendphotobook.dtos.request.DeletarPublicacaoRequest;
import com.example.backendphotobook.dtos.request.ListarComentariosRequest;
import com.example.backendphotobook.dtos.response.CadastrarComentarioResponse;
import com.example.backendphotobook.dtos.response.DeletarComentarioResponse;
import com.example.backendphotobook.dtos.response.ListarComentariosResponse;
import com.example.backendphotobook.entities.ComentariosEntity;
import com.example.backendphotobook.entities.PublicacoesEntity;
import com.example.backendphotobook.entities.UsuariosEntity;
import com.example.backendphotobook.repository.ComentariosRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ComentarioService {

    @Autowired
    private ComentariosRepository comentariosRepository;

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private PublicacaoService publicacaoService;

    public ResponseEntity<CadastrarComentarioResponse> cadastrarNovoComentario(CadastrarComentarioRequest cadastrarComentarioRequest) {
        CadastrarComentarioResponse cadastrarComentarioResponse;

        try {
            salvarComentario(cadastrarComentarioRequest);
            cadastrarComentarioResponse = cadastrarNovoComentarioResponse(201, HttpStatus.CREATED, "Comentario enviado com sucesso.");

        } catch (Exception e) {
            cadastrarComentarioResponse = cadastrarNovoComentarioResponse(400, HttpStatus.BAD_REQUEST, "Erro: " + e.getMessage());
        }

        return ResponseEntity.status(cadastrarComentarioResponse.getHttpStatusCode()).body(cadastrarComentarioResponse);
    }

    private void salvarComentario(CadastrarComentarioRequest cadastrarComentarioRequest) {

        ComentariosEntity comentariosEntity = new ComentariosEntity();
        BeanUtils.copyProperties(cadastrarComentarioRequest, comentariosEntity);

        UsuariosEntity usuariosEntity = usuariosService.findById(Long.parseLong(cadastrarComentarioRequest.getUsuarioId()));

        PublicacoesEntity publicacoesEntity = publicacaoService.findById(Long.parseLong(cadastrarComentarioRequest.getPublicacaoId()));

        Date dataDeCadastro = new Date();

        comentariosEntity.setDataDeCadastro(dataDeCadastro);
        comentariosEntity.setUsuarioId(usuariosEntity);
        comentariosEntity.setPublicacaoId(publicacoesEntity);

        comentariosRepository.save(comentariosEntity);
    }

    private CadastrarComentarioResponse cadastrarNovoComentarioResponse(int httpStatusCode, HttpStatus httpStatus, String mensagem) {
        CadastrarComentarioResponse cadastrarComentarioResponse = new CadastrarComentarioResponse();

        cadastrarComentarioResponse.setHttpStatusCode(httpStatusCode);
        cadastrarComentarioResponse.setHttpStatus(httpStatus);
        cadastrarComentarioResponse.setMensagem(mensagem);

        return cadastrarComentarioResponse;
    }

    public ResponseEntity<List<ListarComentariosResponse>> listarComentarios(ListarComentariosRequest listarComentariosRequest) {

        List<ListarComentariosResponse> comentariosResponseList = new ArrayList<>();
        PublicacoesEntity publicacoesEntity = publicacaoService.findById(Long.parseLong(listarComentariosRequest.getPublicacaoId()));
        Optional<List<ComentariosEntity>> comentariosEntitiesList = comentariosRepository.findByPublicacaoIdOrderByDataDeCadastroDesc(publicacoesEntity);

        if (comentariosEntitiesList.isPresent()){
            for (ComentariosEntity comentariosEntity : comentariosEntitiesList.get()) {
                ListarComentariosResponse listarComentariosResponse = new ListarComentariosResponse();

                if (comentariosEntity.getUsuarioId().getFoto() != null) {
                    listarComentariosResponse.setFoto(Base64.getEncoder().encodeToString(comentariosEntity.getUsuarioId().getFoto()));
                }

                listarComentariosResponse.setComentario(comentariosEntity.getComentario());
                listarComentariosResponse.setNome(comentariosEntity.getUsuarioId().getNome());
                listarComentariosResponse.setDataDeCadastro(comentariosEntity.getDataDeCadastro());

                comentariosResponseList.add(listarComentariosResponse);
            }
        }

        return ResponseEntity.ok().body(comentariosResponseList);
    }

    public ResponseEntity<DeletarComentarioResponse> deletarUmComentario(DeletarComentarioRequest deletarComentarioRequest) {
        DeletarComentarioResponse deletarComentarioResponse;

        try {
            deletarComentario(deletarComentarioRequest);
            deletarComentarioResponse = deletarUmComentarioResponse(200, HttpStatus.OK, "Comentario apagado com sucesso.");

        } catch (Exception e) {
            deletarComentarioResponse = deletarUmComentarioResponse(400, HttpStatus.BAD_REQUEST, "Erro: " + e.getMessage());
        }

        return ResponseEntity.status(deletarComentarioResponse.getHttpStatusCode()).body(deletarComentarioResponse);
    }

    private DeletarComentarioResponse deletarUmComentarioResponse(int httpStatusCode, HttpStatus httpStatus, String mensagem) {
        DeletarComentarioResponse deletarComentarioResponse = new DeletarComentarioResponse();

        deletarComentarioResponse.setHttpStatusCode(httpStatusCode);
        deletarComentarioResponse.setHttpStatus(httpStatus);
        deletarComentarioResponse.setMensagem(mensagem);

        return deletarComentarioResponse;
    }

    private void deletarComentario(DeletarComentarioRequest deletarComentarioRequest) {
        long publicacaoId = Long.parseLong(deletarComentarioRequest.getPublicacaoId());
        long usuarioId = Long.parseLong(deletarComentarioRequest.getUsuarioId());
        long comentarioId = Long.parseLong(deletarComentarioRequest.getComentarioId());

        ComentariosEntity comentariosEntity = findById(comentarioId);

        if (usuarioId == comentariosEntity.getUsuarioId().getId()){
            comentariosRepository.delete(comentariosEntity);
        } else {
            throw new RuntimeException("Usuário não autorizado a excluir esse comentario");
        }
    }

    private ComentariosEntity findById(long comentarioId) {
        Optional<ComentariosEntity> comentariosEntity = comentariosRepository.findById(comentarioId);

        if (comentariosEntity.isPresent()) {
            return comentariosEntity.get();
        } else {
            throw new RuntimeException("Comentario não encontrado");
        }
    }

    public void deletarComentariosDeUmaPublicacao(DeletarPublicacaoRequest deletarPublicacaoRequest) {
        PublicacoesEntity publicacoesEntity = publicacaoService.findById(Long.parseLong(deletarPublicacaoRequest.getPublicacaoId()));
        Optional<List<ComentariosEntity>> comentariosEntityList = comentariosRepository.findByPublicacaoId(publicacoesEntity);

        if (comentariosEntityList.isPresent()) {
            comentariosRepository.deleteAll(comentariosEntityList.get());
        }
    }
}
