package com.example.bookfinder.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import com.example.bookfinder.model.DadosSecaoWikipedia;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import java.util.List;

public class ConsumoAPI {
    // Adicionado tempo limite de conexão no HttpClient
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    // 1. Buscar livros por autor
    public String buscarPorAutor(String nomeAutor) throws Exception {
        String urlFormatada = nomeAutor.replace(" ", "+");
        String uri = "https://openlibrary.org/search.json?author=" + urlFormatada + "&limit=5&fields=key,title,author_name,ratings_average,cover_i,first_publish_year";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("User-Agent", "BooklyApp/1.0 (contato@bookly.com)")
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    // 2. Buscar detalhes/descrição pelo nome do livro
    public String buscarDetalhesLivro(String tituloLivro) throws Exception {
        String urlFormatada = tituloLivro.replace(" ", "+");
        String uri = "https://openlibrary.org/search.json?title=" + urlFormatada + "&limit=1&fields=title,first_publish_year,number_of_pages_median,author_name,key,cover_i,ratings_average";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("User-Agent", "BooklyApp/1.0 (contato@bookly.com)")
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    // 3. Buscar detalhes/biografia do autor pelo NOME
    public String buscarDetalhesAutor(String nomeAutor) throws Exception {
        String urlFormatada = nomeAutor.replace(" ", "+");
        String uri = "https://openlibrary.org/search/authors.json?q=" + urlFormatada + "&limit=1&fields=key,death_date,name,birth_date,top_work,work_count";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("User-Agent", "BooklyApp/1.0 (contato@bookly.com)")
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String buscarBioAutor(String chave) throws Exception {
        String uri = "https://openlibrary.org/authors/" + chave + ".json";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("User-Agent", "BooklyApp/1.0 (contato@bookly.com)")
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        return response.body();
    }

    public String buscarBiografiaWikipedia(String nomeAutor) throws Exception {

        String nomeFormatado = nomeAutor.replace(" ", "_");

        String uri = "https://pt.wikipedia.org/api/rest_v1/page/summary/"
                + nomeFormatado;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("User-Agent", "BooklyApp/1.0 (contato@bookly.com)")
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() == 200) {
            return response.body();
        }

        return null;
    }

    public String buscarDescricaoLivro(String chaveLivro) throws Exception {

        String chave = chaveLivro.replace("/works/", "");

        String uri = "https://openlibrary.org/works/" + chave + ".json";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("User-Agent", "BooklyApp/1.0 (contato@bookly.com)")
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() == 200) {
            return response.body();
        }

        return null;
    }

    public List<DadosSecaoWikipedia> buscarSinopseWikipedia(String tituloLivro) throws Exception {

        String tituloFormatado = tituloLivro.replace(" ", "_");

        String uri = "https://pt.wikipedia.org/w/api.php"
                + "?action=parse"
                + "&page=" + tituloFormatado
                + "&prop=sections"
                + "&format=json";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("User-Agent", "BooklyApp/1.0 (contato@bookly.com)")
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();

        JsonNode raiz = mapper.readTree(response.body());

        JsonNode secoes = raiz
                .get("parse")
                .get("sections");

        List<DadosSecaoWikipedia> resultado = new java.util.ArrayList<>();

        for (JsonNode secao : secoes) {

            String titulo = secao.get("line").asText();
            String indice = secao.get("index").asText();

            resultado.add(
                    new DadosSecaoWikipedia(titulo, indice)
            );
        }


        return resultado;
    }

    public String buscarTextoSecaoWikipedia(String tituloLivro, String indiceSecao) throws Exception {
        String tituloFormatado = tituloLivro.replace(" ", "_");

        String uri = "https://pt.wikipedia.org/w/api.php"
                + "?action=parse"
                + "&page=" + tituloFormatado
                + "&section=" + indiceSecao
                + "&prop=text"
                + "&format=json";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("User-Agent", "BooklyApp/1.0 (contato@bookly.com)")
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode raiz = mapper.readTree(response.body());

        if (raiz.has("parse") && raiz.get("parse").has("text")) {
            String htmlText = raiz.get("parse").get("text").get("*").asText();

            String textoLimpo = htmlText.replaceAll("(?s)<style.*?>.*?</style>", "")
                    .replaceAll("(?s)<script.*?>.*?</script>", "");

            textoLimpo = textoLimpo.replaceAll("(?s)<sup.*?>.*?</sup>", "");

            textoLimpo = textoLimpo.replace("&nbsp;", " ")
                    .replace("&#160;", " ")
                    .replace("&#91;", "[")
                    .replace("&#93;", "]")
                    .replace("&quot;", "\"")
                    .replace("&amp;", "&");

            textoLimpo = textoLimpo.replaceAll("<[^>]*>", "");

            textoLimpo = textoLimpo.replaceAll("(?i)^(enredo|sinopse|trama|argumento)\\[editar\\s*\\|\\s*editar\\s*código\\]", "");

            textoLimpo = textoLimpo.replaceAll("↑.*", "");


            textoLimpo = textoLimpo.replaceAll("\\[\\d+\\]", "");

            textoLimpo = textoLimpo.replaceAll("\\s+", " ").trim();

            return textoLimpo;
        }

        return null;
    }
}