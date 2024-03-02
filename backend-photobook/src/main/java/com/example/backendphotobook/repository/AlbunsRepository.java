package com.example.backendphotobook.repository;

import com.example.backendphotobook.entities.AlbunsEntity;
import com.example.backendphotobook.entities.PublicacoesEntity;
import com.example.backendphotobook.entities.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbunsRepository extends JpaRepository<AlbunsEntity, Long> {
    List<AlbunsEntity> findByUsuarioId(UsuariosEntity usuariosEntity);
}
