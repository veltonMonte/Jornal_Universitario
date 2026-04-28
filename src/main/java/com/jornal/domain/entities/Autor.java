package com.jornal.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Email(message = "E-mail inválido")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String matricula;

    private String curso;
    private String bio;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "universidade_id")
    private Universidade universidade;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    public Autor() {}

    public Autor(String nome, String email, String matricula, String curso, Universidade universidade) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.curso = curso;
        this.universidade = universidade;
    }

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMatricula() { return matricula; }
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getFotoPerfil() { return fotoPerfil; }
    public void setFotoPerfil(String fotoPerfil) { this.fotoPerfil = fotoPerfil; }
    public Universidade getUniversidade() { return universidade; }
    public void setUniversidade(Universidade universidade) { this.universidade = universidade; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
}
