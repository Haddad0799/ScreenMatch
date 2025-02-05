package com.project.screenmatch.service;

import com.project.screenmatch.dto.DadoOmdbTitulo;
import com.project.screenmatch.dto.SerieDto;
import com.project.screenmatch.infra.exceptions.TituloNotFoundException;
import com.project.screenmatch.model.Episodio;
import com.project.screenmatch.model.Serie;
import com.project.screenmatch.infra.repository.SerieRepository;
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
         return buscarSerieOmdb(tituloPesquisado);

        }
        return serieDbOpt.map(SerieDto::new).orElseThrow(TituloNotFoundException::new);

    }

    private SerieDto buscarSerieOmdb(String tituloPesquisado) {
        DadoOmdbTitulo dadoOmdbTitulo = buscarTituloOmdbService.buscarTituloOmdb(tituloPesquisado);

        if (dadoOmdbTitulo.tipo().equalsIgnoreCase("series")) {

            Serie serieOmdb = new Serie(dadoOmdbTitulo);

            //TradutorChatGptService.obterTraducao(serieOmdb.getSinopse());

            List<Episodio> episodiosSerieOmdb = buscarTituloOmdbService.buscarEpisodiosOmb(serieOmdb);
            episodiosSerieOmdb.forEach(e -> e.setSerie(serieOmdb));

            serieOmdb.setEpisodios(episodiosSerieOmdb);

            serieRepository.save(serieOmdb);

            return new SerieDto(serieOmdb);
        }
        throw new TituloNotFoundException();
    }

    public SerieDto buscarSerie(Long id) {
        Serie serie = serieRepository.findById(id).orElseThrow(TituloNotFoundException::new);

        return new SerieDto(serie);
    }
}
