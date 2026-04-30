package com.jornal.application.services;

import com.jornal.application.Dtos.UsuarioRequest;
import com.jornal.application.Dtos.UsuarioResponse;
import com.jornal.domain.entities.Universidade;
import com.jornal.domain.entities.Usuario;
import com.jornal.domain.interfaces.IUsuarioRepository;
import com.jornal.domain.valueobjects.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    // 🔴 Resolvendo "Cannot resolve symbol": Declarar as variáveis finais
    private final IUsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UniversidadeService universidadeService;

    // 🔴 Injetando todas no construtor
    public UsuarioService(IUsuarioRepository repository,
                          PasswordEncoder passwordEncoder,
                          UniversidadeService universidadeService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.universidadeService = universidadeService;
    }

    public UsuarioResponse cadastrar(UsuarioRequest req) {
        if (repository.existsByEmailIgnoreCase(req.getEmail()))
            throw new IllegalArgumentException("Já existe um usuário com o e-mail: " + req.getEmail());

        // 🟢 Busca a universidade pelo nome (conforme mudamos anteriormente)
        Universidade universidade = universidadeService.buscarPorNome(req.getNomeUniversidade());

        Usuario usuario = new Usuario(
                req.getNome(),
                req.getEmail(),
                passwordEncoder.encode(req.getSenha()),
                Role.LEITOR,
                universidade
        );

        return UsuarioResponse.de(repository.save(usuario));
    }

    public UsuarioResponse atualizar(UUID id, UsuarioRequest req) {
        Usuario usuario = encontrar(id); // Retorna Usuario

        // 🔴 Resolvendo "Incompatible types":
        // Certifique-se de atribuir a universidade ao campo correto do usuário
        Universidade uni = universidadeService.buscarPorNome(req.getNomeUniversidade());

        usuario.setNome(req.getNome());
        usuario.setEmail(req.getEmail());
        usuario.setUniversidade(uni); // Altera o campo dentro do objeto Usuario

        if (req.getSenha() != null && !req.getSenha().isBlank()) {
            usuario.setSenha(passwordEncoder.encode(req.getSenha()));
        }

        // Salva o usuario (esperado pelo repository) e não a universidade
        return UsuarioResponse.de(repository.save(usuario));
    }

    public void alterarSenha(UUID id, String novaSenha) {
        Usuario usuario = encontrar(id);
        usuario.setSenha(passwordEncoder.encode(novaSenha));
        repository.save(usuario);
    }

    public UsuarioResponse buscarPorId(UUID id) {
        return UsuarioResponse.de(encontrar(id));
    }

    public List<UsuarioResponse> listarTodos() {
        return repository.findAll().stream()
                .map(UsuarioResponse::de)
                .collect(Collectors.toList());
    }

    public void desativar(UUID id) {
        Usuario u = encontrar(id);
        u.desativar();
        repository.save(u);
    }

    public void remover(UUID id) {
        repository.deleteById(id);
    }

    public Usuario encontrar(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + id));
    }
}