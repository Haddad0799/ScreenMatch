package com.project.screenmatch.useCase;

public interface ConsumirApi {
    String buscarDados(String endereco);
    <T> T converteDados(String json, Class<T> classe);
}
