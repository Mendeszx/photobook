package com.example.backendphotobook.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CadastrarNovoAlbumRequest {

    private String descricao;
    private MultipartFile[] imagens;
    private String usuarioId;
}
