package com.example.backendphotobook.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CadastrarPublicacaoRequest {

    private String descricao;
    private MultipartFile foto;
    private String usuarioId;
}
