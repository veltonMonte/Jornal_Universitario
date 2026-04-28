package com.jornal.application.usecases;

import com.jornal.domain.entities.Artigo;
import com.jornal.domain.interfaces.IArtigoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PublicarArtigo {

    private final IArtigoRepository artigoRepository;

    public PublicarArtigo(IArtigoRepository artigoRepository) {
        this.artigoRepository = artigoRepository;
    }

    public Artigo executar(UUID artigoId) {
        Artigo artigo = artigoRepository.findById(artigoId)
                .orElseThrow(() -> new IllegalArgumentException("Artigo não encontrado: " + artigoId));

        artigo.publicar();

        return artigoRepository.save(artigo);
    }
}