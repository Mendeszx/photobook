package com.example.backendphotobook.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ListarPublicacoesResponse {

    private String descricao;
    private String imagem;
    private int curtidas;
    private String nome;
    private String foto;
    private LocalDate dataDeCadastro;
}
