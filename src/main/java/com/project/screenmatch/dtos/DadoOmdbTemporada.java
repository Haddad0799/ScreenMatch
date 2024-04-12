package com.project.screenmatch.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.screenmatch.model.Episodio;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadoOmdbTemporada(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Season") Integer temporada,
        @JsonAlias("totalSeasons") Integer totalTemporadas,
        @JsonAlias("Episodes")List<Episodio> episodios
        ) {
}
