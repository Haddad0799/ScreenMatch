package com.project.screenmatch.model;

import com.project.screenmatch.dtos.DadoOmdbTitulo;
import com.project.screenmatch.service.TradutorChatGptService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.OptionalDouble;

@Getter
@Entity
@Table(name = "Filmes")
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdScreenmatch;
    @Column(unique = true)
    private String titulo;
    private String ano;
    private String  dataDeLancamento;
    private String tempoDeDuracao;
    @Enumerated(EnumType.STRING)
    private Categoria genero;
    private String diretor;
    private String roteirista;
    private String atores;
    private String sinopse;
    private String idioma;
    private String pais;
    private String premiacoes;
    private String urlDaImagem;
    @Setter
    private Double nota;
    @Setter
    private Long votos;

    public Filme(DadoOmdbTitulo dadoOmdb) {
    this.titulo = dadoOmdb.titulo();
    this.ano = dadoOmdb.ano();
    this.dataDeLancamento = dadoOmdb.dataDeLancamento();
    this.tempoDeDuracao = dadoOmdb.tempoDeDuracao();
    this.genero = Categoria.fromString(dadoOmdb.genero().split(",")[0].trim());
    this.diretor = dadoOmdb.diretor();
    this.roteirista = dadoOmdb.roteirista();
    this.atores = dadoOmdb.atores();
    this.sinopse = TradutorChatGptService.obterTraducao(dadoOmdb.sinopse());
    this.idioma = dadoOmdb.idioma();
    this.pais = dadoOmdb.pais();
    this.premiacoes = dadoOmdb.premiacoes();
    this.urlDaImagem = dadoOmdb.urlDaImagem();
    this.nota = OptionalDouble.of(Double.parseDouble(dadoOmdb.nota())).orElse(0.0);
    this.votos = Long.parseLong(dadoOmdb.votos().replace(",", ""));
    }

    public Filme() {}

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
