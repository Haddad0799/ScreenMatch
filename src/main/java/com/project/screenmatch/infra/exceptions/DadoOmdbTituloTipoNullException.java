package com.project.screenmatch.infra.exceptions;

import com.project.screenmatch.dtos.DadoOmdbTitulo;

public class DadoOmdbTituloTipoNullException extends RuntimeException {
    public DadoOmdbTituloTipoNullException(DadoOmdbTitulo dadoOmdbTitulo) {
        super("Não foi possível identificar o titulo pesquisado" +
                "\n Verifique o conteúdo da resposta: " + dadoOmdbTitulo);
    }
}
