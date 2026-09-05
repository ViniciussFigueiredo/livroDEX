package com.example.bookfinder.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class Autor {
    private String chave;
    private String nomeAutor;
    private String anoDeNascimento;
    private String melhorObra;
    private String biografia;
    private String numeroDeObras;



    public Autor (DadosAutor dadosAutor) {
        this.chave = dadosAutor.chave();
        this.biografia = dadosAutor.getBiografiaTratada();
        this.melhorObra = dadosAutor.melhorObra();
        this.nomeAutor = dadosAutor.nomeAutor();
        this.numeroDeObras = dadosAutor.numeroDeObras();
        this.anoDeNascimento = dadosAutor.anoDeNascimento();

    }

    public String getNumeroDeObras() {
        return numeroDeObras;
    }

    public void setNumeroDeObras(String numeroDeObras) {
        this.numeroDeObras = numeroDeObras;
    }

    public String getMelhorLivro() {
        return melhorObra;
    }

    public void setMelhorLivro(String melhorLivro) {
        this.melhorObra = melhorLivro;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public String getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public void setAnoDeNascimento(String anoDeNascimento) {
        this.anoDeNascimento = anoDeNascimento;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
}
