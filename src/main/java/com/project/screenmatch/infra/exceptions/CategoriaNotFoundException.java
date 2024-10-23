package com.project.screenmatch.infra.exceptions;

public class CategoriaNotFoundException extends RuntimeException{
    public CategoriaNotFoundException(String genero) {
        super("Categoria não encontrada para o genero: " + genero);
    }
}
