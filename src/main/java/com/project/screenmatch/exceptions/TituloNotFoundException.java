package com.project.screenmatch.exceptions;

public class TituloNotFoundException extends RuntimeException{
    public TituloNotFoundException(){
        super("Titulo não encontrado.");
    }
}
