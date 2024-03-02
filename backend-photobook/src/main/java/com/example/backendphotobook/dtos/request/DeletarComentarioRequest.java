package com.example.backendphotobook.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeletarComentarioRequest {

    private String publicacaoId;
    private String comentarioId;
    private String usuarioId;
}
