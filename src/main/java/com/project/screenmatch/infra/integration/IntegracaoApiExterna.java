package com.project.screenmatch.infra.integration;

public interface IntegracaoApiExterna {

    <T> T buscarDados(Class<T> tipoDeRetorno, String endpoint);


}
