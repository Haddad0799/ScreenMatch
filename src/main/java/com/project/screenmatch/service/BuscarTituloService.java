package com.project.screenmatch.service;

import com.project.screenmatch.dtos.DadoOmdbTitulo;
import com.project.screenmatch.dtos.FilmeDto;
import com.project.screenmatch.dtos.SerieDto;
import com.project.screenmatch.integration.IntegracaoApiOmdbService;
import com.project.screenmatch.model.Episodio;
import com.project.screenmatch.model.Filme;
import com.project.screenmatch.model.Serie;
import com.project.screenmatch.model.UrlConstrutor;
import com.project.screenmatch.repositorys.FilmeRepository;
import com.project.screenmatch.repositorys.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarTituloService {

    private final IntegracaoApiOmdbService integracaoApiOmdbService;

    private final UrlConstrutor urlConstrutor;

    private final FilmeRepository filmeRepository;

    private final SerieRepository serieRepository;

    private final BuscarEpisodiosService buscarEpisodiosService;



    public BuscarTituloService(FilmeRepository filmeRepository,
                               SerieRepository serieRepository,
                               IntegracaoApiOmdbService integracaoApiOmdbService,
                               UrlConstrutor urlConstrutor,
                               BuscarEpisodiosService buscarEpisodiosService) {
        this.filmeRepository = filmeRepository;
        this.serieRepository = serieRepository;
        this.integracaoApiOmdbService = integracaoApiOmdbService;
        this.urlConstrutor = urlConstrutor;
        this.buscarEpisodiosService = buscarEpisodiosService;
    }

    public  <T> T buscarTitulo(String tituloPesquisado, Class<T> tipoDeRetorno) {

        // 1. Busca no repositório de filmes
        Filme filmeDb = (Filme) filmeRepository.findByTituloContainingIgnoreCase(tituloPesquisado);
        if (filmeDb != null && tipoDeRetorno.isAssignableFrom(FilmeDto.class)) {
            return tipoDeRetorno.cast(new FilmeDto(filmeDb));
        }

        // 2. Busca no repositório de séries
        Serie serieDb = (Serie) serieRepository.findByTituloContainingIgnoreCase(tituloPesquisado);
        if (serieDb != null && tipoDeRetorno.isAssignableFrom(SerieDto.class)) {
            return tipoDeRetorno.cast(new SerieDto(serieDb));
        }

        // 3. Busca na API externa OMDB
        DadoOmdbTitulo dadoOmdbTitulo = integracaoApiOmdbService.buscarDados(
                DadoOmdbTitulo.class,
                urlConstrutor.construirUrl(tituloPesquisado)
        );

        if (dadoOmdbTitulo.tipo().equalsIgnoreCase("movie") && tipoDeRetorno.isAssignableFrom(FilmeDto.class)) {
            Filme filmeOmdb = filmeRepository.save(new Filme(dadoOmdbTitulo));
            return tipoDeRetorno.cast(new FilmeDto(filmeOmdb));
        }

        if (dadoOmdbTitulo.tipo().equalsIgnoreCase("series") && tipoDeRetorno.isAssignableFrom(SerieDto.class)) {
            Serie serieOmdb = new Serie(dadoOmdbTitulo);
            List<Episodio> episodiosOmb= buscarEpisodiosService.buscarEpisodiosOmb(serieOmdb);
            serieOmdb.setEpisodios(episodiosOmb);

         return tipoDeRetorno.cast(new SerieDto(serieOmdb));
        }

        throw new RuntimeException("Erro 500: Título não encontrado.");
    }
}
