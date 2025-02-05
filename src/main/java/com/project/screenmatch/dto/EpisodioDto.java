package com.project.screenmatch.dto;

import com.project.screenmatch.domain.entities.Episodio;

public record EpisodioDto(
        Integer temporada,
        String titulo,
        Integer episodio,
        String dataLancamento,
        Double nota
) {

    public EpisodioDto(Episodio episodio) {
        this(episodio.getTemporada(),
                episodio.getTitulo(),
                episodio.getNumeroEpisodio(),
                episodio.getDataLancamento(),
                episodio.getNota());
    }
}
