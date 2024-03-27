package com.project.screenmatch.model;

import com.project.screenmatch.dtos.DadoOmdb;
import jakarta.persistence.Entity;

import java.util.OptionalDouble;

@Entity
public class Serie {
    private String titulo;
    private Integer temporadas;
    private String ano;
    private String dataDeLancamento;
    private String tempoDeDuracao;
    private Categoria genero;
    private String diretor;
    private String roteirista;
    private String atores;
    private String sinopse;
    private String idioma;
    private String pais;
    private String premiacoes;
    private String urlDaImagem;
    private Double nota;
    private Long votos;

    public Serie(DadoOmdb dadoOmdb) {
        this.titulo = dadoOmdb.titulo();
        this.temporadas = dadoOmdb.temporadas();
        this.ano = dadoOmdb.ano();
        this.dataDeLancamento = dadoOmdb.dataDeLancamento();
        this.tempoDeDuracao = dadoOmdb.tempoDeDuracao();
        this.genero = Categoria.fromString(dadoOmdb.genero().trim());
        this.diretor = dadoOmdb.diretor();
        this.roteirista = dadoOmdb.roteirista();
        this.atores = dadoOmdb.atores();
        this.sinopse = dadoOmdb.sinopse();
        this.idioma = dadoOmdb.idioma();
        this.pais = dadoOmdb.pais();
        this.premiacoes = dadoOmdb.premiacoes();
        this.urlDaImagem = dadoOmdb.urlDaImagem();
        this.nota = OptionalDouble.of(Double.parseDouble(dadoOmdb.nota())).orElse(0.0);
        this.votos = Long.parseLong(dadoOmdb.nota().replace(",", ""));
    }

    @Override
    public String toString() {
        return """
                Titulo:%s
                Temporadas:%d
                Ano:%s
                Data de Lançamento:%s
                Tempo de duração:%s
                Gênero:%s
                Diretor:%s
                Roteirista:%s
                Atores:%s
                Sinopse:%s
                Idioma:%s
                País:%s
                Premiações:%s
                Imagem:%s
                Nota:%.1f
                Quantidade de votos:%d
                """.formatted(titulo, temporadas, ano, dataDeLancamento, tempoDeDuracao, genero, diretor
                , roteirista, atores, sinopse, idioma, pais, premiacoes, urlDaImagem, nota, votos);
    }
}
