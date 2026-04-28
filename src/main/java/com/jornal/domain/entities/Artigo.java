package com.jornal.domain.entities;

import com.jornal.domain.valueobjects.StatusArtigo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "artigos")
public class Artigo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    private String subtitulo;

    @Column(columnDefinition = "TEXT")
    private String conteudo;

    @Column(unique = true)
    private String slug;

    @Enumerated(EnumType.STRING)
    private StatusArtigo status;

    @Column(name = "imagem_capa")
    private String imagemCapa;

    @ElementCollection
    @CollectionTable(name = "artigo_tags", joinColumns = @JoinColumn(name = "artigo_id"))
    @Column(name = "tag")
    private List<String> tags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_publicacao")
    private LocalDateTime dataPublicacao;

    public Artigo() {}

    public Artigo(String titulo, String conteudo, Autor autor, Categoria categoria) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.autor = autor;
        this.categoria = categoria;
        this.status = StatusArtigo.RASCUNHO;
        this.slug = gerarSlug(titulo);
    }

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }

    private String gerarSlug(String titulo) {
        return java.text.Normalizer.normalize(titulo, java.text.Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .toLowerCase().trim()
                .replaceAll("[^a-z0-9\\s]", "")
                .replaceAll("\\s+", "-");
    }

    public boolean podePublicar() {
        return titulo != null && conteudo != null && autor != null && categoria != null;
    }

    public void publicar() {
        if (!podePublicar()) throw new IllegalStateException("Artigo incompleto.");
        this.status = StatusArtigo.PUBLICADO;
        this.dataPublicacao = LocalDateTime.now();
    }

    public void arquivar() { this.status = StatusArtigo.ARQUIVADO; }

    public void enviarParaRevisao() {
        if (!podePublicar()) throw new IllegalStateException("Artigo incompleto.");
        this.status = StatusArtigo.REVISAO;
    }

    public Universidade getUniversidade() {
        return autor != null ? autor.getUniversidade() : null;
    }

    public UUID getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getSubtitulo() { return subtitulo; }
    public void setSubtitulo(String subtitulo) { this.subtitulo = subtitulo; }
    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }
    public String getSlug() { return slug; }
    public StatusArtigo getStatus() { return status; }
    public String getImagemCapa() { return imagemCapa; }
    public void setImagemCapa(String imagemCapa) { this.imagemCapa = imagemCapa; }
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public LocalDateTime getDataPublicacao() { return dataPublicacao; }
}
