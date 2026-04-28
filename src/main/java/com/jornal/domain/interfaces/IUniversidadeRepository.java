package com.jornal.domain.interfaces;

import com.jornal.domain.entities.Universidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUniversidadeRepository extends JpaRepository<Universidade, UUID> {

    Optional<Universidade> findBySigla(String sigla);

    List<Universidade> findByAtivaTrue(); // se você tiver campo "ativa"
}