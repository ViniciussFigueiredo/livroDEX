package com.example.bookfinder.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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

        String texto;

        if (biografia instanceof java.util.Map<?, ?> map) {
            Object value = map.get("value");
            texto = value != null ? value.toString() : "";
        } else {
            texto = biografia.toString();
        }

        // Pega somente a parte em português
        if (texto.contains("---")) {
            texto = texto.split("---")[0];
        }

        // Remove referências como [2], [3], etc.
        texto = texto.replaceAll("\\\\\\[\\d+\\\\\\]", "");

        // Remove espaços duplicados
        texto = texto.replaceAll("\\s+", " ").trim();

        // Limita o tamanho da biografia
        int limite = 400;

        if (texto.length() > limite) {
            texto = texto.substring(0, limite);

            // Evita cortar uma palavra no meio
            int ultimoEspaco = texto.lastIndexOf(" ");

            if (ultimoEspaco > 0) {
                texto = texto.substring(0, ultimoEspaco);
            }

            texto += "...";
        }

        return texto;
    }

    public String getFotoUrl() {
        if (chave != null && !chave.isBlank()) {
            String olid = chave.replace("/authors/", "");

            return "https://covers.openlibrary.org/a/olid/"
                    + olid
                    + "-M.jpg";
        }

        return null;
    }

    public String getDataNascimento () {
        String data = anoDeNascimento;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
        LocalDate dataConvertida = LocalDate.parse(data, formato);
        DateTimeFormatter formatoSaida = DateTimeFormatter.ofPattern("d 'de' MMM 'de' yyyy", Locale.forLanguageTag("pt-BR"));
        return dataConvertida.format(formatoSaida);
    }

    public String getDataFalecimento () {
        String data = anoDeFalecimento;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
        LocalDate dataConvertida = LocalDate.parse(data, formato);
        DateTimeFormatter formatoSaida = DateTimeFormatter.ofPattern("d 'de' MMM 'de' yyyy", Locale.forLanguageTag("pt-BR"));
        return dataConvertida.format(formatoSaida);
    }


}
