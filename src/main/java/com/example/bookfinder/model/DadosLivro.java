package com.example.bookfinder.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties (ignoreUnknown = true)
public record DadosLivro (@JsonAlias ("key") String chave,
                         @JsonAlias ("title") String titulo,
                         @JsonAlias ("author_name") List<String> nomeAutor,
                         @JsonAlias ("ratings_average") String avaliacao,
                         @JsonAlias ("cover_i") String capa,
                         @JsonAlias ("first_publish_year") Integer anoDeLancamento,
                          @JsonAlias ("number_of_pages_median" ) Integer numeroDePaginas){
}