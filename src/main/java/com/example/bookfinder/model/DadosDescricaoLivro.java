package com.example.bookfinder.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosDescricaoLivro(@JsonAlias("description") Object descricao) {
    public String getDescricaoTratada() {

        if (descricao == null) {
            return null;
        }

        if (descricao instanceof String) {
            return (String) descricao;
        }

        if (descricao instanceof java.util.Map<?, ?> mapa) {
            Object valor = mapa.get("value");

            if (valor != null) {
                return valor.toString();
            }
        }

        return null;
    }
}
