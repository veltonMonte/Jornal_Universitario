package com.jornal.presentation.controllers;


import com.jornal.application.Dtos.UniversidadeRequest;
import com.jornal.application.Dtos.UniversidadeResponse;
import com.jornal.application.services.UniversidadeService;
import com.jornal.domain.entities.Universidade;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/universidades")
public class UniversidadeController {

    private final UniversidadeService universidadeService;

    public UniversidadeController(UniversidadeService universidadeService) {
        this.universidadeService = universidadeService;
    }

    // 🔵 LISTAR
    @GetMapping
    public ResponseEntity<List<UniversidadeResponse>> listar() {
        List<UniversidadeResponse> lista = universidadeService.listarAtivas()
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(lista);
    }

    // 🔵 BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<UniversidadeResponse> buscarPorId(@PathVariable UUID id) {
        Universidade u = universidadeService.buscarPorId(id);
        return ResponseEntity.ok(toResponse(u));
    }

    // 🔵 BUSCAR POR NOME
    @GetMapping("/nome/{nome}")
    public ResponseEntity<UniversidadeResponse> buscarPorNome(@PathVariable String nome) {
        Universidade u = universidadeService.buscarPorNome(nome);
        return ResponseEntity.ok(toResponse(u));
    }

    // 🔵 CADASTRAR
    @PostMapping
    public ResponseEntity<UniversidadeResponse> cadastrar(
            @RequestBody @Valid UniversidadeRequest request
    ) {
        Universidade criada = universidadeService.cadastrar(
                request.getNome(),
                request.getSigla(),
                request.getCidade(),
                request.getEstado(),
                request.getCnpj(),
                request.getCorHex(),
                request.getEmail(),
                request.getSenha()
        );

        return ResponseEntity.ok(toResponse(criada));
    }

    // 🔵 ATUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<UniversidadeResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid UniversidadeRequest request
    ) {
        Universidade atualizada = universidadeService.atualizar(
                id,
                request.getNome(),
                request.getCidade(),
                request.getEstado(),
                request.getSite()
        );

        return ResponseEntity.ok(toResponse(atualizada));
    }

    // 🔵 DESATIVAR
    @PutMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable UUID id) {
        universidadeService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    // 🔵 REMOVER
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        universidadeService.remover(id);
        return ResponseEntity.noContent().build();
    }


    private UniversidadeResponse toResponse(Universidade u) {
        UniversidadeResponse dto = new UniversidadeResponse();

        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setSigla(u.getSigla());
        dto.setCidade(u.getCidade());
        dto.setEstado(u.getEstado());
        dto.setSite(u.getSite());

        dto.setCorHex(u.getCor() != null ? u.getCor().getValor() : null);

        dto.setCnpj(u.getCnpj());
        dto.setAtiva(u.isAtiva());
        dto.setDataCadastro(u.getDataCadastro());

        return dto;
    }
}