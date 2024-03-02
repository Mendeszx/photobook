package com.example.backendphotobook.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CadastroUsuarioRequest {

    private String nome;
    private String email;
    private String senha;
}
