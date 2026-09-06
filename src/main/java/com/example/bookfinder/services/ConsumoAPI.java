package com.example.bookfinder.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    private final HttpClient client = HttpClient.newHttpClient();

    // 1. Buscar livros por autor
    public String buscarPorAutor(String nomeAutor) throws Exception {
        String urlFormatada = nomeAutor.replace(" ", "+");
        String uri = "https://openlibrary.org/search.json?author=" + urlFormatada + "&fields=key,title,author_name,ratings_average,cover_i,first_publish_year";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }


    // 2. Buscar detalhes/descrição pelo nome do livro
    public String buscarDetalhesLivro(String tituloLivro) throws Exception {
        String urlFormatada = tituloLivro.replace(" ", "+");
        String uri = "https://openlibrary.org/search.json?title=" + urlFormatada + "&limit=1";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }


    // 3. Buscar detalhes/biografia do autor pelo NOME
    public String buscarDetalhesAutor(String nomeAutor) throws Exception {
        String urlFormatada = nomeAutor.replace(" ", "+");
        String uri = "https://openlibrary.org/search/authors.json?q=" + urlFormatada + "&limit=1";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
