package com.jornal.presentation.controllers;

import com.jornal.application.Dtos.UsuarioRequest;
import com.jornal.application.Dtos.UsuarioResponse;
import com.jornal.application.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // 🔐 Somente ADMIN pode registrar novos usuários agora
    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@Valid @RequestBody UsuarioRequest request) {
        // Se o código chegar aqui, o 400 sumiu!
        usuarioService.cadastrar(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable UUID id, @Valid @RequestBody UsuarioRequest req) {
        return ResponseEntity.ok(usuarioService.atualizar(id, req));
    }

    // 🔑 ATUALIZAR SENHA ESPECIFICAMENTE
    @PatchMapping("/{id}/senha")
    public ResponseEntity<Void> alterarSenha(@PathVariable UUID id, @RequestBody String novaSenha) {
        usuarioService.alterarSenha(id, novaSenha);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscar(@PathVariable UUID id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PatchMapping("/{id}/desativar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> desativar(@PathVariable UUID id) {
        usuarioService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        usuarioService.remover(id);
        return ResponseEntity.noContent().build();
    }
}