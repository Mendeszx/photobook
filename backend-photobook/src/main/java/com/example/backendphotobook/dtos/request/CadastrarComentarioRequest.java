package com.example.backendphotobook.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CadastrarComentarioRequest {

    private String comentario;
    private String usuarioId;
    private String publicacaoId;
}
