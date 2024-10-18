package com.project.screenmatch.service;

import com.project.screenmatch.dtos.DadoOmdbTitulo;
import com.project.screenmatch.dtos.FilmeDto;
import com.project.screenmatch.dtos.SerieDto;
import com.project.screenmatch.model.Episodio;
import com.project.screenmatch.model.Filme;
import com.project.screenmatch.model.Serie;
import com.project.screenmatch.repositorys.FilmeRepository;
import com.project.screenmatch.repositorys.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TituloService {

    private final BuscarTituloOmdbService buscarTituloOmdbService;
    private final FilmeRepository filmeRepository;
    private final SerieRepository serieRepository;

    public TituloService(BuscarTituloOmdbService buscarTituloOmdbService, FilmeRepository filmeRepository, SerieRepository serieRepository) {
        this.buscarTituloOmdbService = buscarTituloOmdbService;
        this.filmeRepository = filmeRepository;
        this.serieRepository = serieRepository;
    }

    public FilmeDto buscarFilme(String tituloPesquisado) {
        Optional<Filme> filmeDbOpt = filmeRepository.findByTituloContainingIgnoreCase(tituloPesquisado);

        if (filmeDbOpt.isEmpty()) {
            DadoOmdbTitulo dadoOmdbTitulo = buscarTituloOmdbService.buscarTituloOmdb(tituloPesquisado);
            Filme filmeOmdb = new Filme(dadoOmdbTitulo);
            filmeRepository.save(filmeOmdb);
            return new FilmeDto(filmeOmdb);
        }

        Filme filmeDb = filmeDbOpt.get();
        return new FilmeDto(filmeDb);
    }

    public SerieDto buscarSerie(String tituloPesquisado) {
        Optional<Serie> serieDbOpt = serieRepository.findByTituloContainingIgnoreCase(tituloPesquisado);

        if (serieDbOpt.isEmpty()) {
            DadoOmdbTitulo dadoOmdbTitulo = buscarTituloOmdbService.buscarTituloOmdb(tituloPesquisado);
            Serie serieOmdb = new Serie(dadoOmdbTitulo);
            List<Episodio> episodiosSerieOmdb = buscarTituloOmdbService.buscarEpisodiosOmb(serieOmdb);
            episodiosSerieOmdb.forEach(e -> e.setSerie(serieOmdb));
            serieOmdb.setEpisodios(episodiosSerieOmdb);
            serieRepository.save(serieOmdb);
            return new SerieDto(serieOmdb);
        }

        Serie serieDb = serieDbOpt.get();
        return new SerieDto(serieDb);
    }
}
