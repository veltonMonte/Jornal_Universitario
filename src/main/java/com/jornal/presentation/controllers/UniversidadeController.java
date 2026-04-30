package com.jornal.presentation.controllers;

import com.jornal.application.services.UniversidadeService;
import com.jornal.domain.entities.Universidade;
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

    @GetMapping
    public ResponseEntity<List<Universidade>> listar() {
        return ResponseEntity.ok(universidadeService.listarAtivas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Universidade> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(universidadeService.buscarPorId(id));
    }

    // ✅ Ajustado para o nome correto do método no Service
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Universidade> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(universidadeService.buscarPorNome(nome));
    }

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

    // 🔵 Método PUT para atualizar dados
    @PutMapping("/{id}")
    public ResponseEntity<Universidade> atualizar(@PathVariable UUID id, @RequestBody Universidade universidade) {
        Universidade atualizada = universidadeService.atualizar(
                id,
                universidade.getNome(),
                universidade.getCidade(),
                universidade.getEstado(),
                universidade.getSite()
        );
        return ResponseEntity.ok(atualizada);
    }

    @PutMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable UUID id) {
        universidadeService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        universidadeService.remover(id);
        return ResponseEntity.noContent().build();
    }
}