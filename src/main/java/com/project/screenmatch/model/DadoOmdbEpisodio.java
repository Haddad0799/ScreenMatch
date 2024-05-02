package com.project.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadoOmdbEpisodio(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Episode") Integer episodio,
        @JsonAlias("Released") String dataLancamento,
        @JsonAlias("imdbRating") String nota
) {
}
