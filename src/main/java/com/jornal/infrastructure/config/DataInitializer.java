package com.jornal.infrastructure.config;

import com.jornal.domain.entities.Categoria;
import com.jornal.domain.interfaces.ICategoriaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ICategoriaRepository categoriaRepository;

    public DataInitializer(ICategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void run(String... args) {
        if (categoriaRepository.count() == 0) {
            categoriaRepository.saveAll(List.of(
                    new Categoria("Geral"),
                    new Categoria("Eventos"),
                    new Categoria("Cultura"),
                    new Categoria("Notícias"),
                    new Categoria("Acadêmico"),
                    new Categoria("Carreiras"),
                    new Categoria("Esportes"),
                    new Categoria("Tecnologia")
            ));
        }
    }
}