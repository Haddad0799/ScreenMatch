package com.project.screenmatch.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadoOmdb(
        @JsonAlias("Title") String titulo,
        @JsonAlias("totalSeasons") Integer temporadas,
        @JsonAlias("Year") String ano,
        @JsonAlias("Released") String dataDeLancamento,
        @JsonAlias("Runtime") String tempoDeDuracao,
        @JsonAlias("Genre") String genero,
        @JsonAlias("Director") String diretor,
        @JsonAlias("Writer") String roteirista,
        @JsonAlias("Actors") String atores,
        @JsonAlias("Plot") String sinopse,
        @JsonAlias("Language") String idioma,
        @JsonAlias("Country") String pais,
        @JsonAlias("Awards") String premiacoes,
        @JsonAlias("Poster") String urlDaImagem,
        @JsonAlias("imdbRating") String nota,
        @JsonAlias("imdbVotes") String votos
) {
}
