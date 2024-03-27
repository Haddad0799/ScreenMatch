package com.project.screenmatch.model;

import com.project.screenmatch.dtos.DadoOmdb;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.OptionalDouble;

@Getter
@Entity
public class Filme {
    private final String titulo;
    private final String ano;
    private final String  dataDeLancamento;
    private final String tempoDeDuracao;
    private final Categoria genero;
    private final String diretor;
    private final String roteirista;
    private final String atores;
    private final String sinopse;
    private final String idioma;
    private final String pais;
    private final String premiacoes;
    private final String urlDaImagem;
    @Setter
    private Double nota;
    @Setter
    private Long votos;
    public Filme(DadoOmdb dadoOmdb) {
    this.titulo = dadoOmdb.titulo();
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
                """.formatted(titulo,ano,dataDeLancamento,tempoDeDuracao,genero,diretor,roteirista
                ,atores,sinopse,idioma,pais,premiacoes,urlDaImagem,nota,votos);
    }
}
