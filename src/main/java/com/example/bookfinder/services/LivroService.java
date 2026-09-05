package com.example.bookfinder.services;

import com.example.bookfinder.model.DadosLivro;
import com.example.bookfinder.model.Livro;
import com.example.bookfinder.model.ResultadoBuscaLivro;

public class LivroService {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    public Livro buscarEProcessarLivro (String titulo) throws Exception {
        String json = consumoAPI.buscarDetalhesLivro(titulo);
        ResultadoBuscaLivro resposta = conversor.obterDados(json, ResultadoBuscaLivro.class);
        if (resposta.resultadoLivros() == null || resposta.resultadoLivros().isEmpty()) {
            return null;
        } else {
            DadosLivro dadosLivro = resposta.resultadoLivros().get(0);
            return new Livro(dadosLivro);
        }


    }
}
