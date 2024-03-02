package com.example.backendphotobook.repository;

import com.example.backendphotobook.entities.PublicacoesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacoesRepository extends JpaRepository<PublicacoesEntity, Long> {
}
