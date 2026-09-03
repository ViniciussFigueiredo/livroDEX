package com.example.bookfinder.services;

import com.example.bookfinder.model.DadosAutor;
import com.example.bookfinder.model.DadosLivro;
import com.example.bookfinder.model.ResultadoBusca;

import java.util.List;

public class AutorService {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    public DadosAutor buscarInfoAutor (String nomeAutor) throws Exception{
        String json = consumoAPI.buscarDetalhesAutor(nomeAutor);
        return conversor.obterDados(json, DadosAutor.class);
    }

    public List<DadosLivro> buscarLivrosDoAutor (String nomeAutor) throws Exception {
        String json = consumoAPI.buscarPorAutor(nomeAutor);
        ResultadoBusca resposta = conversor.obterDados(json, ResultadoBusca.class);
        return resposta.resultadoLivros();
    }
}
