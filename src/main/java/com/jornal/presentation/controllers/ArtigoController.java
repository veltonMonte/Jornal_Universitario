package com.jornal.presentation.controllers;

import com.jornal.application.Dtos.ArtigoRequest;
import com.jornal.application.Dtos.ArtigoResponse;
import com.jornal.application.services.ArtigoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/artigos")
public class ArtigoController {

    private final ArtigoService artigoService;

    public ArtigoController(ArtigoService artigoService) {
        this.artigoService = artigoService;
    }

    @PostMapping
    public ResponseEntity<ArtigoResponse> criar(@Valid @RequestBody ArtigoRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(artigoService.criar(req));
    }

    @GetMapping
    public ResponseEntity<List<ArtigoResponse>> listar() {
        return ResponseEntity.ok(artigoService.listarPublicados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtigoResponse> buscar(@PathVariable UUID id) {
        return ResponseEntity.ok(artigoService.buscarPorId(id));
    }

    @GetMapping("/universidade/{universidadeId}")
    public ResponseEntity<List<ArtigoResponse>> listarPorUniversidade(@PathVariable UUID universidadeId) {
        return ResponseEntity.ok(artigoService.listarPorUniversidade(universidadeId));
    }

    @PatchMapping("/{id}/publicar")
    public ResponseEntity<ArtigoResponse> publicar(@PathVariable UUID id) {
        return ResponseEntity.ok(artigoService.publicar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtigoResponse> atualizar(@PathVariable UUID id,
                                                    @Valid @RequestBody ArtigoRequest req) {
        return ResponseEntity.ok(artigoService.atualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        artigoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}