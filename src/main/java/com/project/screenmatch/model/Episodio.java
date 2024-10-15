package com.project.screenmatch.model;

import com.project.screenmatch.dtos.DadoOmdbEpisodio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table( name = "episodios")
@Getter
public class Episodio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String titulo;
    private Integer numeroEpisodio;
    private String dataLancamento;
    @Setter
    private double nota;
    @Setter
    @ManyToOne
    private Serie serie;

    public Episodio() {
    }

    public Episodio(DadoOmdbEpisodio dadoOmdbEpisodio) {
        this.titulo = dadoOmdbEpisodio.titulo();
        this.numeroEpisodio = dadoOmdbEpisodio.episodio();
        this.dataLancamento = dadoOmdbEpisodio.dataLancamento();

        if (isNumeric(String.valueOf(dadoOmdbEpisodio.nota()))) {
            this.nota = Double.parseDouble(String.valueOf(dadoOmdbEpisodio.nota()));
        } else {
            this.nota = 0.0;
        }

    }

    private boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
