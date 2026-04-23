package com.jornal.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Email(message = "Email inválido")
    @Column(nullable = false, unique = true)
    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    private PerfilUsuario perfil;

    public enum PerfilUsuario {
        USER,
        ADMIN
    }
}