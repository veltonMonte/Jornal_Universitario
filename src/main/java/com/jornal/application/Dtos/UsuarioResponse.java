package com.jornal.application.Dtos;


import com.jornal.domain.entities.Usuario;
import com.jornal.domain.valueobjects.Role;
import java.time.LocalDateTime;
import java.util.UUID;

public class UsuarioResponse {

    private UUID id;
    private String nome;
    private String email;
    private Role role;
    private boolean ativo;
    private LocalDateTime dataCadastro;

    public static UsuarioResponse de(Usuario u) {
        UsuarioResponse r = new UsuarioResponse();
        r.id = u.getId();
        r.nome = u.getNome();
        r.email = u.getEmail();
        r.role = u.getRole();
        r.ativo = u.isAtivo();
        r.dataCadastro = u.getDataCadastro();
        return r;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public Role getRole() { return role; }
    public boolean isAtivo() { return ativo; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
}