package com.example.backendphotobook.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DeletarPublicacaoRequest {

    private String publicacaoId;
    private String usuarioId;
}
