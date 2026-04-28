package com.jornal.application.usecases;

import com.jornal.domain.entities.Artigo;
import com.jornal.domain.interfaces.IArtigoRepository;
import com.jornal.domain.valueobjects.StatusArtigo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class BuscarPorCategoria {

    private final IArtigoRepository artigoRepository;

    public BuscarPorCategoria(IArtigoRepository artigoRepository) {
        this.artigoRepository = artigoRepository;
    }

    public List<Artigo> executar(UUID categoriaId) {
        return artigoRepository.findByCategoriaIdAndStatus(categoriaId, StatusArtigo.PUBLICADO);
    }
}