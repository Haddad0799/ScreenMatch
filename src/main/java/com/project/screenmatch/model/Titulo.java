package com.project.screenmatch.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Define a estratégia de herança
@Table(name = "Titulos")
public class Titulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    protected String titulo;

    protected String ano;
    protected String dataDeLancamento;
    protected String tempoDeDuracao;

    @Enumerated(EnumType.STRING)
    protected Categoria genero;

    protected String diretor;
    protected String roteirista;
    protected String atores;
    protected String sinopse;
    protected String idioma;
    protected String pais;
    protected String premiacoes;
    protected String urlDaImagem;

    @Setter
    protected Double nota;
    @Setter
    protected Long votos;

    public Titulo() {
    }

}


