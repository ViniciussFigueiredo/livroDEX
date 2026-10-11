package com.example.bookfinder.model;


public class Autor {
    private String chave;
    private String nomeAutor;
    private String anoDeNascimento;
    private String melhorObra;
    private String biografia;
    private String numeroDeObras;
    private String anoDeFalecimento;
    private String fotos;

    public Autor (DadosAutor dadosAutor, String biografia) {



        this.chave = dadosAutor.chave();
        this.biografia = biografia;
        this.melhorObra = dadosAutor.melhorObra();
        this.nomeAutor = dadosAutor.nomeAutor();
        this.numeroDeObras = dadosAutor.numeroDeObras();
        this.anoDeNascimento = dadosAutor.getDataNascimento();
        this.anoDeFalecimento = dadosAutor.getDataFalecimento();
        this.fotos = dadosAutor.getFotoUrl();

    }

    public String getFotos() {
        return fotos;
    }

    public void setFotos(String fotos) {
        this.fotos = fotos;
    }

    public String getMelhorObra() {
        return melhorObra;
    }

    public void setMelhorObra(String melhorObra) {
        this.melhorObra = melhorObra;
    }

    public String getAnoDeFalecimento() {
        return anoDeFalecimento;
    }

    public void setAnoDeFalecimento(String anoDeFalecimento) {
        this.anoDeFalecimento = anoDeFalecimento;
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
