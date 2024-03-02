package com.example.backendphotobook.repository;

import com.example.backendphotobook.entities.ComentariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentariosRepository extends JpaRepository<ComentariosEntity, Long> {
}
