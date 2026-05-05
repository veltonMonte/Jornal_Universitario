package com.jornal.application.Dtos;

public class CadastroUniversidadeRequest {
    private String nome;
    private String sigla;
    private String cidade;
    private String estado;
    private String cnpj;
    private String cor;
    private String email;
    private String senha;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}