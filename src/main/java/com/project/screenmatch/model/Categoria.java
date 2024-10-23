package com.project.screenmatch.model;

import com.project.screenmatch.infra.exceptions.CategoriaNotFoundException;

public enum Categoria {
    ACAO("Action", "Ação"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    ROMANCE("Romance", "Romance"),
    DOCUMENTARIO("Documentary", "Documentário"),
    SUSPENSE("Suspense", "Suspense"),
    TERROR("Terror", "Terror"),
    FICCAOCIENTIFICA("Science fiction", "Ficção Científica"),
    CRIME("Crime", "Crime"),
    AVENTURA("Adventure", "Aventura");

    private final String categoriaOmdb;

    private final String categoriaPtBr;

    Categoria(String categoriaOmdb, String categoriaPtBr) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPtBr = categoriaPtBr;
    }


    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new CategoriaNotFoundException(text);
    }

    public static Categoria fromPortugues(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaPtBr.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new CategoriaNotFoundException(text);
    }


}


