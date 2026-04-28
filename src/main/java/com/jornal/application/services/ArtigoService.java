package com.jornal.application.services;

import com.jornal.domain.entities.Artigo;
import com.jornal.domain.interfaces.IArtigoRepository;
import com.jornal.domain.interfaces.IAutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ArtigoService {

    private final IArtigoRepository artigoRepository;
    private final IAutorRepository autorRepository;

    public ArtigoService(IArtigoRepository artigoRepository, IAutorRepository autorRepository) {
        this.artigoRepository = artigoRepository;
        this.autorRepository = autorRepository;
    }

    public List<Artigo> listarTodos() {
        return artigoRepository.findAll();
    }

    public Artigo buscarPorId(UUID id) {
        return artigoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artigo não encontrado"));
    }

    public Artigo salvar(Artigo artigo) {
        return artigoRepository.save(artigo);
    }

    public void remover(UUID id) {
        artigoRepository.deleteById(id);
    }

    public List<Artigo> listarPorUniversidade(UUID universidadeId) {
        return artigoRepository.findByAutorUniversidadeId(universidadeId);
    }
}