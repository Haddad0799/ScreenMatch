package com.project.screenmatch.model;

import com.project.screenmatch.dtos.DadoOmdbEpisodios;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.OptionalDouble;

@Entity
@Table( name = "episodios")
@Getter
public class Episodio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdScreenmatch;
    private final String titulo;
    private final Integer episodio;
    private final String dataLancamento;
    private final double nota;

    @ManyToOne
    private Serie serie;



    public Episodio(DadoOmdbEpisodios dadoOmdbEpisodios) {
        this.titulo = dadoOmdbEpisodios.titulo();
        this.episodio = dadoOmdbEpisodios.episodio();
        this.dataLancamento = dadoOmdbEpisodios.dataLancamento();
        this.nota = OptionalDouble.of(Double.parseDouble(String.valueOf(dadoOmdbEpisodios.nota())))
                .orElse(0.0);
    }
}
