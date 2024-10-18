package com.project.screenmatch.infra.exceptions;

public class TituloNotFoundException extends RuntimeException{
    public TituloNotFoundException(){
        super("Titulo não encontrado.");
    }
}
