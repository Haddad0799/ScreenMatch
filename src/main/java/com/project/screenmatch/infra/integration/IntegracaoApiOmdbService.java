package com.project.screenmatch.infra.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.screenmatch.infra.exceptions.ErroComunicacaoApiOmdb;
import com.project.screenmatch.infra.exceptions.ErroNaSerializacaoDosDados;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class IntegracaoApiOmdbService implements IntegracaoApiExterna {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T buscarDados(Class<T> tipoDeRetorno, String endpoint) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .build();
        HttpResponse<String> response;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ErroComunicacaoApiOmdb(e.getMessage());
        }
        try{
            return mapper.readValue(response.body(),tipoDeRetorno);
        }catch (JsonProcessingException e) {
            throw new ErroNaSerializacaoDosDados(e.getMessage());
        }
    }
}
