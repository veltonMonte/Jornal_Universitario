package com.jornal.application.Dtos;

import com.jornal.domain.entities.Artigo;
import com.jornal.domain.valueobjects.StatusArtigo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ArtigoResponse {

    private UUID id;
    private String titulo;
    private String subtitulo;
    private String conteudo;
    private String slug;
    private StatusArtigo status;
    private String imagemCapa;
    private List<String> tags;
    private UUID usuarioId;
    private String usuarioNome;
    private UUID categoriaId;
    private String categoriaNome;
    private UUID universidadeId;
    private String universidadeNome;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataPublicacao;

    public static ArtigoResponse de(Artigo a) {
        ArtigoResponse r = new ArtigoResponse();
        r.id = a.getId();
        r.titulo = a.getTitulo();
        r.subtitulo = a.getSubtitulo();
        r.conteudo = a.getConteudo();
        r.slug = a.getSlug();
        r.status = a.getStatus();
        r.imagemCapa = a.getImagemCapa();
        r.tags = a.getTags();
        r.usuarioId = a.getUsuario().getId();
        r.usuarioNome = a.getUsuario().getNome();
        r.categoriaId = a.getCategoria().getId();
        r.categoriaNome = a.getCategoria().getNome();
        r.universidadeId = a.getUniversidade().getId();
        r.universidadeNome = a.getUniversidade().getNome();
        r.dataCriacao = a.getDataCriacao();
        r.dataPublicacao = a.getDataPublicacao();
        return r;
    }

    public UUID getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getSubtitulo() { return subtitulo; }
    public String getConteudo() { return conteudo; }
    public String getSlug() { return slug; }
    public StatusArtigo getStatus() { return status; }
    public String getImagemCapa() { return imagemCapa; }
    public List<String> getTags() { return tags; }
    public UUID getUsuarioId() { return usuarioId; }
    public String getUsuarioNome() { return usuarioNome; }
    public UUID getCategoriaId() { return categoriaId; }
    public String getCategoriaNome() { return categoriaNome; }
    public UUID getUniversidadeId() { return universidadeId; }
    public String getUniversidadeNome() { return universidadeNome; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public LocalDateTime getDataPublicacao() { return dataPublicacao; }
}