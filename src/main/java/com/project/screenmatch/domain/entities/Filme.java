package com.project.screenmatch.domain.entities;

import com.project.screenmatch.domain.model.Categoria;
import com.project.screenmatch.dto.DadoOmdbTitulo;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.OptionalDouble;

@Getter
@Entity
@Table(name = "Filmes")
public class Filme extends Titulo {


    public Filme(DadoOmdbTitulo dadoOmdb) {
        super();
        this.titulo = dadoOmdb.titulo();
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

    public Filme() {}
}