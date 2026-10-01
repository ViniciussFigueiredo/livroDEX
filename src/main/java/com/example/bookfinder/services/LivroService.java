package com.example.bookfinder.services;

import com.example.bookfinder.model.DadosDescricaoLivro;
import com.example.bookfinder.model.DadosLivro;
import com.example.bookfinder.model.Livro;
import com.example.bookfinder.model.ResultadoBuscaLivro;
import com.example.bookfinder.model.DadosSecaoWikipedia;

import java.util.List;

public class LivroService {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    private DadosSecaoWikipedia encontrarSecaoSinopse(List<DadosSecaoWikipedia> secoes) {
        if (secoes == null) return null;

        for (DadosSecaoWikipedia secao : secoes) {
            if (secao.titulo() != null) {
                String titulo = secao.titulo().toLowerCase();

                if (titulo.equals("enredo")
                        || titulo.equals("sinopse")
                        || titulo.equals("trama")
                        || titulo.equals("argumento")) {

                    return secao;
                }
            }
        }

        return null;
    }

    public Livro buscarEProcessarLivro(String titulo) throws Exception {
        String json = consumoAPI.buscarDetalhesLivro(titulo);

        ResultadoBuscaLivro resposta =
                conversor.obterDados(json, ResultadoBuscaLivro.class);

        if (resposta == null || resposta.resultadoLivros() == null || resposta.resultadoLivros().isEmpty()) {
            return null;
        }

        DadosLivro dadosLivro = resposta.resultadoLivros().get(0);
        String descricao = null;


        try {

            List<DadosSecaoWikipedia> secoes = consumoAPI.buscarSinopseWikipedia(dadosLivro.titulo());

            DadosSecaoWikipedia secaoSinopse = encontrarSecaoSinopse(secoes);

            if (secaoSinopse != null && secaoSinopse.indice() != null) {
                // Busca o texto usando o índice retornado
                descricao = consumoAPI.buscarTextoSecaoWikipedia(dadosLivro.titulo(), secaoSinopse.indice());
            }
        } catch (Exception e) {
            System.err.println("Aviso: Falha ao buscar sinopse na Wikipédia: " + e.getMessage());
        }

        // 2. FALLBACK: Se a Wikipédia não encontrou nada, tenta o Open Library
        if (descricao == null || descricao.isBlank()) {
            try {
                String jsonDescricao = consumoAPI.buscarDescricaoLivro(dadosLivro.chave());

                if (jsonDescricao != null) {
                    DadosDescricaoLivro dadosDescricao = conversor.obterDados(jsonDescricao, DadosDescricaoLivro.class);
                    if (dadosDescricao != null) {
                        descricao = dadosDescricao.getDescricaoTratada();
                    }
                }
            } catch (Exception e) {
                System.err.println("Aviso: Falha ao buscar descrição na Open Library: " + e.getMessage());
            }
        }

        // 3. SE AMBOS FALHAREM OU TRATAMENTO DE TAMANHO
        if (descricao == null || descricao.isBlank()) {
            descricao = "Sinopse não disponível no momento.";
        } else if (descricao.length() > 600) {
            descricao = descricao.substring(0, 600) + "...";
        }

        return new Livro(dadosLivro, descricao);
    }
}