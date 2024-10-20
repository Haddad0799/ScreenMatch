package com.project.screenmatch.infra.exceptions;

public class TituloNotPresentException extends RuntimeException{
    public TituloNotPresentException() {
        super("Não existem titulos para esse contexto.");
    }
}
