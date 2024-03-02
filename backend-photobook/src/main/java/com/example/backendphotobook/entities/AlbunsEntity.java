package com.example.backendphotobook.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "albuns")
@Getter
@Setter
public class AlbunsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "curtidas")
    private int curtidas;
    @ManyToOne
    @JoinColumn(nullable = false, name = "usuario_id", referencedColumnName = "id", unique = true)
    private UsuariosEntity usuarioId;
    @Column(nullable = false, name = "data_de_cadastro")
    private Date dataDeCadastro;
}
