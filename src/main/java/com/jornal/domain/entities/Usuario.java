package com.jornal.domain.entities;

import com.jornal.domain.valueobjects.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Email(message = "E-mail inválido")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private boolean ativo;

    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "universidade_id")
    private Universidade universidade;


    public Usuario() {}

    public Usuario(String nome, String email, String senha, Role role, Universidade universidade) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
        this.universidade = universidade;
        this.ativo = true;
    }

    @PrePersist
    public void prePersist() { this.dataCadastro = LocalDateTime.now(); }

    public void desativar() { this.ativo = false; }
    public void ativar()    { this.ativo = true;  }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public boolean isAtivo() { return ativo; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }

    public Universidade getUniversidade() { return universidade; }
    public void setUniversidade(Universidade universidade) { this.universidade = universidade; }
}