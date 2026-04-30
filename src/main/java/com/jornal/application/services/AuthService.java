package com.jornal.application.services;

import com.jornal.application.Dtos.LoginRequest;
import com.jornal.application.Dtos.LoginResponse;
import com.jornal.domain.entities.Usuario;
import com.jornal.domain.interfaces.IUsuarioRepository;
import com.jornal.infrastructure.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final IUsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(IUsuarioRepository repository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse login(LoginRequest req) {
        Usuario usuario = repository.findByEmailIgnoreCase(req.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("E-mail ou senha inválidos"));

        if (!usuario.isAtivo())
            throw new IllegalStateException("Usuário inativo");

        if (!passwordEncoder.matches(req.getSenha(), usuario.getSenha()))
            throw new IllegalArgumentException("E-mail ou senha inválidos");

        String token = jwtUtil.gerar(usuario.getEmail(), usuario.getRole().name());
        return new LoginResponse(token, usuario.getNome(), usuario.getEmail(), usuario.getRole());
    }
}