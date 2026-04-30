package com.jornal.domain.interfaces;

import com.jornal.domain.entities.Artigo;
import com.jornal.domain.valueobjects.StatusArtigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IArtigoRepository extends JpaRepository<Artigo, UUID> {
    Optional<Artigo> findBySlug(String slug);
    List<Artigo> findByStatus(StatusArtigo status);
    List<Artigo> findByCategoriaId(UUID categoriaId);
    List<Artigo> findByUsuarioId(UUID usuarioId);
    List<Artigo> findByUsuarioUniversidadeId(UUID universidadeId);
    List<Artigo> findByCategoriaIdAndStatus(UUID categoriaId, StatusArtigo status);

    @Query("SELECT a FROM Artigo a WHERE LOWER(a.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR LOWER(a.conteudo) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Artigo> buscarPorTermo(@Param("termo") String termo);
}