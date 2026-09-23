package com.example.bookfinder.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosWikipedia(@JsonAlias ("extract") String biografia) {
}
