package com.project.screenmatch.model;

import com.project.screenmatch.dtos.DadoOmdbTitulo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Getter
@Entity
@Table(name = "Series")
public class Serie extends Titulo {
    private Integer temporadas;

    @Setter
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episodio> episodios = new ArrayList<>();

    public Serie(DadoOmdbTitulo dadoOmdb) {
        super();
        this.titulo = dadoOmdb.titulo();
        this.temporadas = dadoOmdb.temporadas();
        this.ano = dadoOmdb.ano();
        this.dataDeLancamento = dadoOmdb.dataDeLancamento();
        this.tempoDeDuracao = dadoOmdb.tempoDeDuracao();
        this.genero = Categoria.fromString(dadoOmdb.genero().split(",")[0].trim());
        this.diretor = dadoOmdb.diretor();
        this.roteirista = dadoOmdb.roteirista();
        this.atores = dadoOmdb.atores();
        this.sinopse = dadoOmdb.sinopse();
        this.idioma = dadoOmdb.idioma();
        this.pais = dadoOmdb.pais();
        this.premiacoes = dadoOmdb.premiacoes();
        this.urlDaImagem = dadoOmdb.urlDaImagem();
        this.nota = OptionalDouble.of(Double.parseDouble(dadoOmdb.nota())).orElse(0.0);
        this.votos = Long.parseLong(dadoOmdb.votos().replace(",", ""));
    }

    public Serie() {}
}