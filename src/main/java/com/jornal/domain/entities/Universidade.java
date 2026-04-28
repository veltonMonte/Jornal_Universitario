package com.jornal.domain.entities;

import com.jornal.domain.valueobjects.CorHex;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "universidades")
public class Universidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 20)
    private String sigla;

    private String cidade;
    private String estado;
    private String site;

    @Column(name = "cor_hex", length = 7)
    private String cor;

    private boolean ativa;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    public Universidade() {}

    public Universidade(String nome, String sigla, String cidade, String estado) {
        this.nome = nome;
        this.sigla = sigla;
        this.cidade = cidade;
        this.estado = estado;
        this.ativa = true;
    }

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
    }

    public void desativar() { this.ativa = false; }
    public void ativar() { this.ativa = true; }

    public void setCor(CorHex corHex) {
        this.cor = corHex != null ? corHex.getValor() : null;
    }

    public CorHex getCor() {
        return cor != null ? new CorHex(cor) : null;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getSite() { return site; }
    public void setSite(String site) { this.site = site; }
    public boolean isAtiva() { return ativa; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
}
