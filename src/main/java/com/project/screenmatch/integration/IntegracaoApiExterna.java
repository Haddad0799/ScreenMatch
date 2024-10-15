package com.project.screenmatch.integration;

public interface IntegracaoApiExterna {

    <T> T buscarDados(Class<T> tipoDeRetorno, String endpoint);


}
