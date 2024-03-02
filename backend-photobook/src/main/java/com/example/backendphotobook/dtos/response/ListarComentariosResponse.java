package com.example.backendphotobook.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ListarComentariosResponse {

    private String comentario;
    private String nome;
    private String foto;
    private LocalDate dataDeCadastro;
}
