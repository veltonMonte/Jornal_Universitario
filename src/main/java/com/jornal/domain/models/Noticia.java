package com.jornal.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "noticias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String conteudo;

    private String resumo;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataPublicacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editor_id")
    private Usuario editor;

    @OneToMany(mappedBy = "noticia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Comentario> comentarios = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }

    public enum Categoria {
        ACADEMICA,
        ESPORTES,
        CULTURA,
        TECNOLOGIA,
        POLITICA,
        GERAL
    }
}