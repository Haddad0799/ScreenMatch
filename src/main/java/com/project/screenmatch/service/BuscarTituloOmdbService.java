package com.project.screenmatch.service;

import com.project.screenmatch.dtos.DadoOmdbTemporada;
import com.project.screenmatch.dtos.DadoOmdbTitulo;
import com.project.screenmatch.integration.IntegracaoApiOmdbService;
import com.project.screenmatch.model.Episodio;
import com.project.screenmatch.model.Serie;
import com.project.screenmatch.util.UrlConstrutor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarTituloOmdbService {

    private final IntegracaoApiOmdbService integracaoApiOmdbService;

    private final UrlConstrutor urlConstrutor;


    public BuscarTituloOmdbService(IntegracaoApiOmdbService integracaoApiOmdbService,
                                   UrlConstrutor urlConstrutor) {
        this.integracaoApiOmdbService = integracaoApiOmdbService;
        this.urlConstrutor = urlConstrutor;

    }

    public DadoOmdbTitulo buscarTituloOmdb(String tituloPesquisado) {

        DadoOmdbTitulo dadoOmdbTitulo = integracaoApiOmdbService.buscarDados(
                DadoOmdbTitulo.class,
                urlConstrutor.construirUrl(tituloPesquisado)
        );

        if (dadoOmdbTitulo != null) {
            return dadoOmdbTitulo;
        }
        throw new RuntimeException("erro");
    }

    public List<Episodio> buscarEpisodiosOmb(Serie serie) {

        List<Episodio> episodios = List.of();

        for (int i = 1; i <= serie.getTemporadas(); i++) {
            DadoOmdbTemporada dadoOmdbTemporada = integracaoApiOmdbService
                    .buscarDados(DadoOmdbTemporada.class, urlConstrutor.construirUrl(serie.getTitulo(), i));

            episodios = dadoOmdbTemporada.episodios().stream()
                    .map(Episodio::new)
                    .toList();
            serie.setEpisodios(episodios);
        }
        serie.setEpisodios(episodios);
        return episodios;
    }
}
