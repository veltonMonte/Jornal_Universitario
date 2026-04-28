package com.jornal.application.usecases;

import com.jornal.domain.entities.Artigo;
import com.jornal.domain.interfaces.IArtigoRepository;
import com.jornal.domain.valueobjects.StatusArtigo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListarArtigos {

    private final IArtigoRepository artigoRepository;

    public ListarArtigos(IArtigoRepository artigoRepository) {
        this.artigoRepository = artigoRepository;
    }

    public List<Artigo> executar() {
        return artigoRepository.findByStatus(StatusArtigo.PUBLICADO);
    }
}