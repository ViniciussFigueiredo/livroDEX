package com.example.bookfinder.services;

import com.example.bookfinder.model.DadosLivro;
import com.example.bookfinder.model.ResultadoBusca;

import java.util.List;

public class LivroService {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    public List<DadosLivro> buscarEProcessarLivro (String titulo) throws Exception {
        String json = consumoAPI.buscarDetalhesLivro(titulo);
        ResultadoBusca resposta = conversor.obterDados(json, ResultadoBusca.class);
        return resposta.resultadoLivros();
    }
}
