package com.example.bookfinder.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosSecaoWikipedia(
        @JsonAlias("line") String titulo,
        @JsonAlias("index") String indice
) {
}
