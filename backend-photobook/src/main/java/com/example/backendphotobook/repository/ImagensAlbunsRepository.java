package com.example.backendphotobook.repository;

import com.example.backendphotobook.entities.AlbunsEntity;
import com.example.backendphotobook.entities.ComentariosEntity;
import com.example.backendphotobook.entities.ImagensAlbunsEntity;
import com.example.backendphotobook.entities.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImagensAlbunsRepository extends JpaRepository<ImagensAlbunsEntity, Long> {
    Optional<List<ImagensAlbunsEntity>> findByAlbumId(AlbunsEntity albunsEntity);
}
