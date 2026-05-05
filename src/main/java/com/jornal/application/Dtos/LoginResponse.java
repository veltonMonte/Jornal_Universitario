package com.jornal.application.Dtos;

import com.jornal.domain.valueobjects.Role;

public class LoginResponse {
    private String token;
    private String nome;
    private String email;
    private String role;
    private String universidadeId;
    private String universidadeSigla;

    public LoginResponse(String token, String nome, String email, Role role, String universidadeId, String universidadeSigla) {
        this.token = token;
        this.nome = nome;
        this.email = email;
        this.role = role.name();
        this.universidadeId = universidadeId;
        this.universidadeSigla = universidadeSigla;
    }

    // getters...
}