package com.jornal.domain.interfaces;

import com.jornal.domain.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IAutorRepository extends JpaRepository<Autor, UUID> {

    Optional<Autor> findByEmail(String email);

    Optional<Autor> findByMatricula(String matricula);
}