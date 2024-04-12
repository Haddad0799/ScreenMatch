package com.project.screenmatch.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadoOmdbEpisodios(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Released") Integer episodio,
        @JsonAlias("Episode") String dataLancamento,
        @JsonAlias("imdbRating") double nota
) {
}
