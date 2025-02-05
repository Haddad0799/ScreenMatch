package com.project.screenmatch.dto;

import com.project.screenmatch.domain.model.Categoria;
import com.project.screenmatch.domain.entities.Filme;

public record FilmeDto(
        long id,
        String titulo,
        String ano,
        String dataDeLancamento,
        String tempoDeDuracao,
        Categoria genero,
        String diretor,
        String roteirista,
        String atores,
        String sinopse,
        String idioma,
        String pais,
        String premiacoes,
        String urlDaImagem,
        Double nota,
        Long votos
) {
    public FilmeDto(Filme filme) {
        this(filme.getId(),
                filme.getTitulo(),
                filme.getAno(),
                filme.getDataDeLancamento(),
                filme.getTempoDeDuracao(),
                filme.getGenero(),
                filme.getDiretor(),
                filme.getRoteirista(),
                filme.getAtores(),
                filme.getSinopse(),
                filme.getIdioma(),
                filme.getPais(),
                filme.getPremiacoes(),
                filme.getUrlDaImagem(),
                filme.getNota(),
                filme.getVotos());
    }
}
