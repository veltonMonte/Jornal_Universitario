package com.jornal.application.Dtos;

import com.jornal.domain.valueobjects.Role;

public class LoginResponse {

    private String token;
    private String nome;
    private String email;
    private Role role;

    public LoginResponse(String token, String nome, String email, Role role) {
        this.token = token;
        this.nome = nome;
        this.email = email;
        this.role = role;
    }

    public String getToken() { return token; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public Role getRole() { return role; }
}