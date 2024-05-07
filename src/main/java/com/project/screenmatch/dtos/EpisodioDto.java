package com.project.screenmatch.dtos;

public record EpisodioDto(
        Integer temporada,
        String titulo,
        Integer episodio,
        String dataLancamento,
        Double nota
) {

}
