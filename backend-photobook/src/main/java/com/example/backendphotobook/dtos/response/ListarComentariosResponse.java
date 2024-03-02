package com.example.backendphotobook.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ListarComentariosResponse {

    private String comentario;
    private String nome;
    private String foto;
    private Date dataDeCadastro;
}
