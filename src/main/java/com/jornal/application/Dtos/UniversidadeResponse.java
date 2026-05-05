package com.jornal.application.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UniversidadeResponse {

    private UUID id;
    private String nome;
    private String sigla;
    private String cidade;
    private String estado;
    private String site;
    private String corHex;
    private String cnpj;
    private String email;
    private boolean ativa;
    private LocalDateTime dataCadastro;


}