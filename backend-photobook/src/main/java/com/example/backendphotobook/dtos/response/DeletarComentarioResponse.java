package com.example.backendphotobook.dtos.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DeletarComentarioResponse {

    private int httpStatusCode;
    private HttpStatus httpStatus;
    private String mensagem;
}
