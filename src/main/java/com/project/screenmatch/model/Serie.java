package com.project.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.project.screenmatch.dtos.DadoOmdb;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Serie {
    private String titulo;
    private Integer temporadas;
    private String ano;
    private Date dataDeLancamento;
    private String tempoDeDuracao;
    private Cateoria genero;
    private String diretor;
    private String roteirista;
    private String atores;
    private String sinopse;
    private String idioma;
    private String pais;
    private String premiacoes;
    private String urlDaImagem;
    private String nota;
    private String votos;
    public Serie(DadoOmdb dadoOmdb) {

    }
}
