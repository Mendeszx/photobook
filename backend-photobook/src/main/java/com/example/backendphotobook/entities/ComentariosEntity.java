package com.example.backendphotobook.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "comentarios")
@Getter
@Setter
public class ComentariosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false, name = "publicacao_id", referencedColumnName = "id", unique = true)
    private PublicacoesEntity publicacaoId;
    @ManyToOne
    @JoinColumn(nullable = false, name = "usuario_id", referencedColumnName = "id", unique = true)
    private UsuariosEntity usuarioId;
    @Column(nullable = false, name = "comentario")
    private String comentario;
    @Column(nullable = false, name = "data_de_cadastro")
    private LocalDate dataDeCadastro;
}
