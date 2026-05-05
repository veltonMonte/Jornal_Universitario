package com.jornal.application.services;

import com.jornal.domain.entities.Universidade;
import com.jornal.domain.entities.Usuario;
import com.jornal.domain.interfaces.IUniversidadeRepository;
import com.jornal.domain.interfaces.IUsuarioRepository;
import com.jornal.domain.valueobjects.CorHex;
import com.jornal.domain.valueobjects.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UniversidadeService {

    private final IUniversidadeRepository universidadeRepository;
    private final IUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UniversidadeService(IUniversidadeRepository universidadeRepository,
                               IUsuarioRepository usuarioRepository,
                               PasswordEncoder passwordEncoder) {
        this.universidadeRepository = universidadeRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Universidade buscarPorNome(String nome) {
        return universidadeRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> new IllegalArgumentException("Universidade não encontrada com o nome: " + nome));
    }

    public Universidade buscarPorId(UUID id) {
        return universidadeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Universidade não encontrada com o ID: " + id));
    }

    public Universidade cadastrar(String nome, String sigla, String cidade, String estado, String cnpj, String cor, String emailAdmin, String senhaAdmin) {
        universidadeRepository.findBySigla(sigla).ifPresent(u -> {
            throw new IllegalArgumentException("Já existe uma universidade com a sigla: " + sigla);
        });

        Universidade universidade = new Universidade(nome, sigla, cidade, estado, cnpj);

        if (cor != null) universidade.setCor(new CorHex(cor));

        Universidade salva = universidadeRepository.save(universidade);

        Usuario admin = new Usuario(nome, emailAdmin, passwordEncoder.encode(senhaAdmin), Role.ADMIN, salva);
        usuarioRepository.save(admin);

        return salva;
    }

    public Universidade atualizar(UUID id, String nome, String cidade, String estado, String site) {
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