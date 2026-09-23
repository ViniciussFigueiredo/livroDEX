package com.example.bookfinder.services;

import com.example.bookfinder.model.*;

import java.util.Collections;
import java.util.List;

public class AutorService {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    public Autor buscarInfoAutor(String nomeAutor) throws Exception {

        String json = consumoAPI.buscarDetalhesAutor(nomeAutor);

        ResultadoBuscaAutor resposta =
                conversor.obterDados(json, ResultadoBuscaAutor.class);

        if (resposta.resultadoAutor() == null || resposta.resultadoAutor().isEmpty()) {
            return null;
        }

        DadosAutor dadosAutor = resposta.resultadoAutor().get(0);

        String jsonWikipedia = consumoAPI.buscarBiografiaWikipedia(nomeAutor);

        String biografiaWikipedia = null;

        if (jsonWikipedia != null) {
            DadosWikipedia dadosWikipedia =
                    conversor.obterDados(jsonWikipedia, DadosWikipedia.class);

            biografiaWikipedia = dadosWikipedia.biografia();
        }

        String biografia = biografiaWikipedia;

        if (biografia == null || biografia.isBlank()) {
            biografia = dadosAutor.getBiografiaTratada();
        }

        if (biografia.length() > 400) {
            biografia = biografia.substring(0, 400) + "...";
        }

        return new Autor(dadosAutor, biografia);
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
