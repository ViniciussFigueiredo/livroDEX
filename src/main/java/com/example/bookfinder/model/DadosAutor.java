package com.example.bookfinder.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosAutor (@JsonAlias ("key") String chave,
                         @JsonAlias ("author_name") String nomeAutor,
                         @JsonAlias ("birth_date") Integer anoDeNascimento,
                          @JsonAlias ("bio") String biografia){
}
