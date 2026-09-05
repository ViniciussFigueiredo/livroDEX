package com.example.bookfinder.model;

import java.util.List;

public class Livro {
    private String chave;
    private String titulo;
    private List<String> nomeAutor;
    private Double avaliacao;
    private String capa;
    private Integer anoDeLancamento;

    public Livro (DadosLivro dadosLivro) {
        this.chave = dadosLivro.chave();
        this.titulo = dadosLivro.titulo();
        this.nomeAutor = dadosLivro.nomeAutor();
        this.anoDeLancamento = dadosLivro.anoDeLancamento();
        this.capa = dadosLivro.capa();

        try {
            this.avaliacao = Double.valueOf(dadosLivro.avaliacao());
        } catch (NullPointerException ex) {
            this.avaliacao = 0.0;
        }

    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(List<String> nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public Integer getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(Integer anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    @Override
    public String toString() {
        return """
                Titulo da obra: %s
                Ano de lançamento: %s
                Avaliação: %.1f
                """.formatted(titulo, anoDeLancamento != null ? anoDeLancamento : "Não informado", avaliacao);
    }
}
