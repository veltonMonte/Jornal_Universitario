package com.jornal.repositories;

import com.jornal.domain.models.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, UUID> {
    List<Noticia> findByAutorId(UUID autorId);
    List<Noticia> findByCategoria(Noticia.Categoria categoria);
}