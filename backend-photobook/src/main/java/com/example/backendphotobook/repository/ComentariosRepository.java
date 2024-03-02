package com.example.backendphotobook.repository;

import com.example.backendphotobook.entities.ComentariosEntity;
import com.example.backendphotobook.entities.PublicacoesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComentariosRepository extends JpaRepository<ComentariosEntity, Long> {
    Optional<List<ComentariosEntity>> findByPublicacaoId(PublicacoesEntity publicacao);
    Optional<List<ComentariosEntity>> findByPublicacaoIdOrderByDataDeCadastroDesc(PublicacoesEntity publicacoesEntity);
}
