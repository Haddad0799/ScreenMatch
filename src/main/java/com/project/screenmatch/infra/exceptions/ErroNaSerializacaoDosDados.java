package com.project.screenmatch.infra.exceptions;

public class ErroNaSerializacaoDosDados extends RuntimeException{

    public ErroNaSerializacaoDosDados(String message) {
        super("Erro no processo de serialização dos dados" + message);
    }
}
