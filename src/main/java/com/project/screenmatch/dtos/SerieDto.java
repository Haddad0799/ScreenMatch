package com.project.screenmatch.dtos;

import com.project.screenmatch.model.Categoria;
import com.project.screenmatch.model.Serie;

public record SerieDto(

        long id,
        String titulo,
        Integer temporadas,
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
){

    public SerieDto(Serie serie) {
        this(serie.getId(),
                serie.getTitulo(),
                serie.getTemporadas(),
                serie.getAno(),
                serie.getDataDeLancamento(),
                serie.getTempoDeDuracao(),
                serie.getGenero(),
                serie.getDiretor(),
                serie.getRoteirista(),
                serie.getAtores(),
                serie.getSinopse(),
                serie.getIdioma(),
                serie.getPais(),
                serie.getPremiacoes(),
                serie.getUrlDaImagem(),
                serie.getNota(),
                serie.getVotos());
    }
}

