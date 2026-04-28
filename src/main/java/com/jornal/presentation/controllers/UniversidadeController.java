package com.jornal.presentation.controllers;

import com.jornal.application.services.UniversidadeService;
import com.jornal.domain.entities.Universidade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/universidades")
public class UniversidadeController {

    private final UniversidadeService universidadeService;

    public UniversidadeController(UniversidadeService universidadeService) {
        this.universidadeService = universidadeService;
    }

    // 🔵 LISTAR TODAS
    @GetMapping
    public ResponseEntity<List<Universidade>> listar() {
        return ResponseEntity.ok(universidadeService.listarAtivas());
    }

    // 🔍 BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Universidade> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(universidadeService.buscarPorId(id));
    }

    // 🟢 CRIAR UNIVERSIDADE
    @PostMapping
    public ResponseEntity<Universidade> cadastrar(@RequestBody Universidade universidade) {
        Universidade criada = universidadeService.cadastrar(
                universidade.getNome(),
                universidade.getSigla(),
                universidade.getCidade(),
                universidade.getEstado()
        );

        return ResponseEntity.ok(criada);
    }

    // 🔴 DESATIVAR
    @PutMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable UUID id) {
        universidadeService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    // ❌ REMOVER
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        universidadeService.remover(id);
        return ResponseEntity.noContent().build();
    }
}