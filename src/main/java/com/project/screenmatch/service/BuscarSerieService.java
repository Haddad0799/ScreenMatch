package com.project.screenmatch.service;

import com.project.screenmatch.dtos.DadoOmdbTitulo;
import com.project.screenmatch.dtos.SerieDto;
import com.project.screenmatch.infra.exceptions.TituloNotFoundException;
import com.project.screenmatch.model.Episodio;
import com.project.screenmatch.model.Serie;
import com.project.screenmatch.repositorys.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuscarSerieService {

    private final BuscarTituloOmdbService buscarTituloOmdbService;
    private final SerieRepository serieRepository;

    public BuscarSerieService(BuscarTituloOmdbService buscarTituloOmdbService, SerieRepository serieRepository) {
        this.buscarTituloOmdbService = buscarTituloOmdbService;
        this.serieRepository = serieRepository;
    }

    public SerieDto buscarSerie(String tituloPesquisado) {
        Optional<Serie> serieDbOpt = serieRepository.findByTituloContainingIgnoreCase(tituloPesquisado);

        if (serieDbOpt.isEmpty()) {
            DadoOmdbTitulo dadoOmdbTitulo = buscarTituloOmdbService.buscarTituloOmdb(tituloPesquisado);
            if (dadoOmdbTitulo.tipo().equalsIgnoreCase("series")) {
                Serie serieOmdb = new Serie(dadoOmdbTitulo);
                List<Episodio> episodiosSerieOmdb = buscarTituloOmdbService.buscarEpisodiosOmb(serieOmdb);
                episodiosSerieOmdb.forEach(e -> e.setSerie(serieOmdb));
                serieOmdb.setEpisodios(episodiosSerieOmdb);
                serieRepository.save(serieOmdb);
                return new SerieDto(serieOmdb);
            }

        }
        return serieDbOpt.map(SerieDto::new).orElseThrow(TituloNotFoundException::new);
    }
}
