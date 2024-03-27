package com.project.screenmatch.service;

public interface ConsumirApi {
    String buscarDados(String endereco);
    <T> T converteDados(String json, Class<T> classe);
}
