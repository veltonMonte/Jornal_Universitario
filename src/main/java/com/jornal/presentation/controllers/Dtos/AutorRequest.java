package com.jornal.presentation.controllers.Dtos;

import java.util.UUID;
import lombok.Data;

@Data
public class AutorRequest {

    private String nome;
    private String email;
    private String matricula;
    private String curso;
    private UUID universidadeId;
}