package com.example.backendphotobook.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "imagens_albuns")
@Getter
@Setter
public class ImagensAlbunsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "imagem")
    private byte[] imagem;
    @ManyToOne
    @JoinColumn(nullable = false, name = "album_id", referencedColumnName = "id", unique = true)
    private AlbunsEntity albumId;
}
