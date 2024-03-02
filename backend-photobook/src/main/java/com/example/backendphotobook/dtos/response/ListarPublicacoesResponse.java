package com.example.backendphotobook.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ListarPublicacoesResponse {

    private String descricao;
    private String imagem;
    private int curtidas;
    private String nome;
    private String foto;
    private Date dataDeCadastro;
}
