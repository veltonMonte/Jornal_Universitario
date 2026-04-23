package com.jornal.repositories;

import com.jornal.domain.models.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, UUID> {
    List<Comentario> findByNoticiaId(UUID noticiaId);
    List<Comentario> findByAutorId(UUID autorId);
}