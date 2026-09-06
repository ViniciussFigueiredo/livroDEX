package com.example.bookfinder.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosAutor (@JsonAlias ("key") String chave,
                         @JsonAlias ("name") String nomeAutor,
                         @JsonAlias ("birth_date") String anoDeNascimento,
                          @JsonAlias ("bio") Object biografia,
                          @JsonAlias ("top_work" )String melhorObra,
                          @JsonAlias ("work_count") String numeroDeObras,
                          @JsonAlias("death_date") String anoDeFalecimento){
    public String getBiografiaTratada() {
        if (biografia == null) {
            return "Biografia não disponível.";
        }

        if (biografia instanceof java.util.Map<?, ?> map) {
            Object value = map.get("value");
            return value != null ? value.toString() : "Biografia não disponível.";
        }

        return biografia.toString();
    }


}
