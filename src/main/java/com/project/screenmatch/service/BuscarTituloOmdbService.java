package com.project.screenmatch.service;

import com.project.screenmatch.dto.DadoOmdbTemporada;
import com.project.screenmatch.dto.DadoOmdbTitulo;
import com.project.screenmatch.infra.exceptions.DadoOmdbTituloTipoNullException;
import com.project.screenmatch.infra.integration.IntegracaoApiOmdbService;
import com.project.screenmatch.domain.entities.Episodio;
import com.project.screenmatch.domain.entities.Serie;
import com.project.screenmatch.util.UrlConstrutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        if (dadoOmdbTitulo.tipo() == null) {
            throw new DadoOmdbTituloTipoNullException(dadoOmdbTitulo);
        }
        return dadoOmdbTitulo;

    }

    public List<Episodio> buscarEpisodiosOmb(Serie serie) {

        List<Episodio> episodios = new ArrayList<>();

        for (int i = 1; i <= serie.getTemporadas(); i++) {
            int temporada = i;
            DadoOmdbTemporada dadoOmdbTemporada = integracaoApiOmdbService
                    .buscarDados(DadoOmdbTemporada.class, urlConstrutor.construirUrl(serie.getTitulo(), i));

          List<Episodio> episodiosTemporada = dadoOmdbTemporada.episodios().stream()
                    .map(e -> new Episodio(e,temporada))
                    .toList();
          episodios.addAll(episodiosTemporada);
            serie.setEpisodios(episodios);
        }
        return episodios;
    }
}
