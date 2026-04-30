package com.jornal.domain.interfaces;

import com.jornal.domain.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ICategoriaRepository extends JpaRepository<Categoria, UUID> {
}