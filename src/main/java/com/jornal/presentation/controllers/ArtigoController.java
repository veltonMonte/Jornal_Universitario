package com.jornal.presentation.controllers;

import com.jornal.application.services.ArtigoService;
import com.jornal.application.usecases.PublicarArtigo;
import com.jornal.domain.entities.Artigo;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/artigos")
public class ArtigoController {

    private final ArtigoService artigoService;
    private final PublicarArtigo publicarArtigo;

    public ArtigoController(ArtigoService artigoService, PublicarArtigo publicarArtigo) {
        this.artigoService = artigoService;
        this.publicarArtigo = publicarArtigo;
    }

    @GetMapping
    public List<Artigo> listar() {
        return artigoService.listarTodos();
    }

    @GetMapping("/universidade/{universidadeId}")
    public List<Artigo> listarPorUniversidade(@PathVariable String universidadeId) {
        return artigoService.listarPorUniversidade(UUID.fromString(universidadeId));
    }

    @GetMapping("/{id}")
    public Artigo buscarPorId(@PathVariable String id) {
        return artigoService.buscarPorId(UUID.fromString(id));
    }

    @PostMapping("/publicar/{id}")
    public Artigo publicar(@PathVariable String id) {
        return publicarArtigo.executar(UUID.fromString(id));
    }

    @PostMapping
    public Artigo criar(@RequestBody Artigo artigo) {
        return artigoService.salvar(artigo);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable String id) {
        artigoService.remover(UUID.fromString(id));
    }
}