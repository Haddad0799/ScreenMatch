package com.project.screenmatch.model;

import com.project.screenmatch.dtos.DadoOmdbEpisodio;
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



    public Episodio(DadoOmdbEpisodio dadoOmdbEpisodio) {
        this.titulo = dadoOmdbEpisodio.titulo();
        this.episodio = dadoOmdbEpisodio.episodio();
        this.dataLancamento = dadoOmdbEpisodio.dataLancamento();
        this.nota = OptionalDouble.of(Double.parseDouble(String.valueOf(dadoOmdbEpisodio.nota())))
                .orElse(0.0);
    }
}
