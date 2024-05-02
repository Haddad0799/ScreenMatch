package com.project.screenmatch.dtos;

import com.project.screenmatch.model.Categoria;

public record FilmeDto(
        long idScreenMatch,
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
}
