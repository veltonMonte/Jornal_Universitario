package com.jornal.application.services;

import com.jornal.domain.entities.Universidade;
import com.jornal.domain.interfaces.IUniversidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UniversidadeService {

    // ✅ Injeção de dependência necessária
    private final IUniversidadeRepository universidadeRepository;

    public UniversidadeService(IUniversidadeRepository universidadeRepository) {
        this.universidadeRepository = universidadeRepository;
    }

    // 🔍 Busca por Nome (Usado pelo UsuarioService no cadastro de alunos)
    public Universidade buscarPorNome(String nome) {
        return universidadeRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> new IllegalArgumentException("Universidade não encontrada com o nome: " + nome));
    }

    // 🔍 Busca por ID (Usado internamente e pelo Controller)
    public Universidade buscarPorId(UUID id) {
        return universidadeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Universidade não encontrada com o ID: " + id));
    }

    // 🟢 Cadastro
    public Universidade cadastrar(String nome, String sigla, String cidade, String estado) {
        universidadeRepository.findBySigla(sigla).ifPresent(u -> {
            throw new IllegalArgumentException("Já existe uma universidade com a sigla: " + sigla);
        });

        Universidade universidade = new Universidade(nome, sigla, cidade, estado);
        return universidadeRepository.save(universidade);
    }

    // 🔵 Atualização (PUT) - Resolve erro de tipos incompatíveis
    public Universidade atualizar(UUID id, String nome, String cidade, String estado, String site) {
        // Correção: Chama o método de busca da própria classe Service
        Universidade universidade = this.buscarPorId(id);

        universidade.setNome(nome);
        universidade.setCidade(cidade);
        universidade.setEstado(estado);
        universidade.setSite(site);

        return universidadeRepository.save(universidade);
    }

    public List<Universidade> listarAtivas() {
        return universidadeRepository.findByAtivaTrue();
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