package com.example.backendphotobook.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ListarAlbunsResponse {

    private String descricao;
    private List<String> imagens;
    private int curtidas;
    private String nome;
    private String foto;
    private LocalDate dataDeCadastro;
}
