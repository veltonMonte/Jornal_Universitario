package com.jornal.application.services;

import com.jornal.domain.entities.Universidade;
import com.jornal.domain.interfaces.IUniversidadeRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UniversidadeService {

    private final IUniversidadeRepository universidadeRepository;

    public UniversidadeService(IUniversidadeRepository universidadeRepository) {
        this.universidadeRepository = universidadeRepository;
    }

    public Universidade cadastrar(String nome, String sigla, String cidade, String estado) {

        universidadeRepository.findBySigla(sigla).ifPresent(u -> {
            throw new IllegalArgumentException("Já existe uma universidade com a sigla: " + sigla);
        });

        Universidade universidade = new Universidade(nome, sigla, cidade, estado);

        return universidadeRepository.save(universidade);
    }

    public Universidade buscarPorId(UUID id) {
        return universidadeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Universidade não encontrada: " + id));
    }

    public List<Universidade> listarAtivas() {
        return universidadeRepository.findByAtivaTrue();
    }

    public Universidade atualizar(UUID id, String nome, String cidade, String estado, String site) {

        Universidade universidade = buscarPorId(id);

        universidade.setNome(nome);
        universidade.setCidade(cidade);
        universidade.setEstado(estado);
        universidade.setSite(site);

        return universidadeRepository.save(universidade);
    }

    public void desativar(UUID id) {

        Universidade universidade = buscarPorId(id);

        universidade.desativar();

        universidadeRepository.save(universidade);
    }

    public void remover(UUID id) {
        universidadeRepository.deleteById(id);
    }
}