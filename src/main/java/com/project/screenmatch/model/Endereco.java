package com.project.screenmatch.model;

import org.springframework.beans.factory.annotation.Value;

public class Endereco {
    @Value("${API_ENDPOINT}")
    private static String ENDERECOBASE;

    @Value("${API_KEY}")
    private static String APIKEY;
    @Value("TEMPORADA")
    private static String TEMPORADAENDPOINT;



    public static String montaEndereco(String pesquisaDoUsuario) {
        return ENDERECOBASE + pesquisaDoUsuario.replace(" " , "+").toLowerCase() + APIKEY;
    }
}
