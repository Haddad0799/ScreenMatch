package com.project.screenmatch.service;

import com.project.screenmatch.dtos.DadoOmdbTemporada;
import com.project.screenmatch.dtos.EpisodioDto;
import com.project.screenmatch.integration.IntegracaoApiOmdbService;
import com.project.screenmatch.model.Episodio;
import com.project.screenmatch.model.Serie;
import com.project.screenmatch.model.UrlConstrutor;
import com.project.screenmatch.repositorys.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuscarEpisodiosService {

    private final SerieRepository serieRepository;

    private final IntegracaoApiOmdbService integracaoApiOmdbService;

    private final UrlConstrutor urlConstrutor;

    public BuscarEpisodiosService(SerieRepository serieRepository, IntegracaoApiOmdbService integracaoApiOmdbService, UrlConstrutor urlConstrutor) {
        this.serieRepository = serieRepository;
        this.integracaoApiOmdbService = integracaoApiOmdbService;
        this.urlConstrutor = urlConstrutor;
    }

    public List<EpisodioDto> buscarEpisodiosDb(Serie serie) {

        Optional<Serie> serieDb = serieRepository.findById(serie.getId());

        if (serieDb.isPresent()) {
            return serieDb.get().getEpisodios()
                    .stream()
                    .map(EpisodioDto::new).toList();
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
        serieRepository.save(serie);
      return episodios;
    }
}
