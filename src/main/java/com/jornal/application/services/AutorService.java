package com.jornal.application.services;

import com.jornal.domain.entities.Autor;
import com.jornal.domain.entities.Universidade;
import com.jornal.domain.interfaces.IAutorRepository;
import com.jornal.domain.interfaces.IUniversidadeRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AutorService {

    private final IAutorRepository autorRepository;
    private final IUniversidadeRepository universidadeRepository;

    public AutorService(IAutorRepository autorRepository,
                        IUniversidadeRepository universidadeRepository) {
        this.autorRepository = autorRepository;
        this.universidadeRepository = universidadeRepository;
    }

    public Autor cadastrar(String nome, String email, String matricula, String curso, UUID universidadeId) {

        autorRepository.findByEmail(email).ifPresent(a -> {
            throw new IllegalArgumentException("Já existe um autor com o e-mail: " + email);
        });

        Universidade universidade = universidadeRepository.findById(universidadeId)
                .orElseThrow(() -> new IllegalArgumentException("Universidade não encontrada: " + universidadeId));

        Autor autor = new Autor(nome, email, matricula, curso, universidade);

        return autorRepository.save(autor);
    }

    public Autor buscarPorId(UUID id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado: " + id));
    }

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    public void remover(UUID id) {
        autorRepository.deleteById(id);
    }
}