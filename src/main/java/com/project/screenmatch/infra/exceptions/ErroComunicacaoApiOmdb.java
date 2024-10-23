package com.project.screenmatch.infra.exceptions;

public class ErroComunicacaoApiOmdb extends RuntimeException{
    public ErroComunicacaoApiOmdb(String message){
        super("Ocorreu um erro de comunicação com a api externa Omdb" + message);
    }
}
