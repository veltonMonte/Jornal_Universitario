package com.jornal.presentation.controllers;

import com.jornal.application.services.AutorService;
import com.jornal.domain.entities.Autor;

import com.jornal.domain.interfaces.IAutorRepository;
import com.jornal.presentation.controllers.Dtos.AutorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;


    public AutorController(AutorService autorService) { this.autorService = autorService; }


    @GetMapping
    public List<Autor> listar() {
        return autorService.listarTodos();
    }

    @GetMapping("/{id}")
    public Autor buscarPorId(@PathVariable UUID id) {
        return autorService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable UUID id) {
        autorService.remover(id);
    }

    @PostMapping
    public Autor salvar(@RequestBody AutorRequest request) {
        return autorService.cadastrar(
                request.getNome(),
                request.getEmail(),
                request.getMatricula(),
                request.getCurso(),
                request.getUniversidadeId()
        );
    }

}