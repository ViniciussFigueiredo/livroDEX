package com.example.bookfinder.services;

import com.example.bookfinder.model.*;

import java.util.Collections;
import java.util.List;

public class AutorService {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    public Autor buscarInfoAutor (String nomeAutor) throws Exception{
        String json = consumoAPI.buscarDetalhesAutor(nomeAutor);
        ResultadoBuscaAutor resposta = conversor.obterDados(json, ResultadoBuscaAutor.class);
        if (resposta.resultadoAutor() == null || resposta.resultadoAutor().isEmpty()) {
            return null;
        } else {
            DadosAutor dadosAutor = resposta.resultadoAutor().get(0);

            String jsonBio = consumoAPI.buscarBioAutor(dadosAutor.chave());

            DadosAutor dadosBio = conversor.obterDados(jsonBio, DadosAutor.class);

            return new Autor(dadosAutor, dadosBio);
        }
    }

    public List <Livro> buscarLivrosDoAutor (String nomeAutor) throws Exception {
        String json = consumoAPI.buscarPorAutor(nomeAutor);
        ResultadoBuscaLivro resposta = conversor.obterDados(json, ResultadoBuscaLivro.class);
        if (resposta.resultadoLivros() == null || resposta.resultadoLivros().isEmpty()) {
            return Collections.emptyList();
        } else {
            return resposta.resultadoLivros().stream()
                    .map(Livro::new)
                    .limit(5)
                    .toList();
        }
    }
}
