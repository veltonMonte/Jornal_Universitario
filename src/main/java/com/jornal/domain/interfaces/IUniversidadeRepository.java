package com.jornal.domain.interfaces;

import com.jornal.domain.entities.Universidade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUniversidadeRepository extends JpaRepository<Universidade, UUID> {

    // Resolve: "Cannot resolve method 'findByAtivaTrue'"
    List<Universidade> findByAtivaTrue();

    // Resolve: "Cannot resolve method 'findByNomeIgnoreCase'"
    Optional<Universidade> findByNomeIgnoreCase(String nome);

    // Usado na validação de cadastro
    Optional<Universidade> findBySigla(String sigla);
}