package com.project.screenmatch.dtos;

import com.project.screenmatch.model.Episodio;

public record EpisodioDto(
        Integer temporada,
        String titulo,
        Integer episodio,
        String dataLancamento,
        Double nota
) {

    public EpisodioDto(Episodio episodio) {
        this(episodio.getSerie().getTemporadas(),
                episodio.getTitulo(),
                episodio.getNumeroEpisodio(),
                episodio.getDataLancamento(),
                episodio.getNota());
    }
}
