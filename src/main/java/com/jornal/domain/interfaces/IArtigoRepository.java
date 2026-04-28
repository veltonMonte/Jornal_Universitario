package com.jornal.domain.interfaces;

import com.jornal.domain.entities.Artigo;
import com.jornal.domain.valueobjects.StatusArtigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IArtigoRepository extends JpaRepository<Artigo, UUID> {

    List<Artigo> findByCategoriaId(UUID categoriaId);

    List<Artigo> findByStatus(StatusArtigo status);

    List<Artigo> findByCategoriaIdAndStatus(UUID categoriaId, StatusArtigo status);

    List<Artigo> findByAutorUniversidadeId(UUID universidadeId);
}