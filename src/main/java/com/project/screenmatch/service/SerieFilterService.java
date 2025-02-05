package com.project.screenmatch.service;

import com.project.screenmatch.dto.EpisodioDto;
import com.project.screenmatch.dto.SerieDto;
import com.project.screenmatch.infra.exceptions.TituloNotFoundException;
import com.project.screenmatch.infra.exceptions.TituloNotPresentException;
import com.project.screenmatch.model.Categoria;
import com.project.screenmatch.model.Episodio;
import com.project.screenmatch.model.Serie;
import com.project.screenmatch.infra.repository.SerieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SerieFilterService {

    private final SerieRepository serieRepository;

    public SerieFilterService( SerieRepository serieRepository) {

        this.serieRepository = serieRepository;
    }

    @Transactional
    public List<SerieDto> seriesTop5(){
        List<Serie>  seriesTop5Db = serieRepository.findTop5ByOrderByNotaDesc()
                .orElseThrow(TituloNotPresentException::new);

        return seriesTop5Db.stream().map(SerieDto::new).toList();
    }

    @Transactional
    public List<SerieDto> seriesLancamentos() {
        List<Serie> seriesLancamentosDb = serieRepository.findByAno("2024")
                .orElseThrow(TituloNotPresentException::new);

        return seriesLancamentosDb
                .stream()
                .map(SerieDto::new)
                .toList();
    }

    @Transactional
    public List<EpisodioDto> allEpisodiosSerie(Long id) {
        List<Episodio> serieEpisodiosDb = serieRepository.allEpisodiosSerie(id)
                .orElseThrow(TituloNotFoundException::new);

        return serieEpisodiosDb
                .stream()
                .map(EpisodioDto::new)
                .toList();

    }

    @Transactional
    public List<EpisodioDto> allEpisodiosSerieTemporada(Long serieId, int temporada) {
        List<Episodio> episodiosTemporadaDb = serieRepository
                .allEpisodiosSerieTemporada(serieId,temporada)
                .orElseThrow(TituloNotFoundException::new);

        return episodiosTemporadaDb
                .stream()
                .map(EpisodioDto::new)
                .toList();
    }

    @Transactional
    public List<EpisodioDto> top5EpisodiosSerie(Long serieId) {
        List<Episodio> topEpisodiosDb = serieRepository.topEpisodiosSerie(serieId)
                .orElseThrow(TituloNotPresentException::new);

        return topEpisodiosDb
                .stream()
                .map(EpisodioDto::new)
                .limit(5)
                .toList();
    }

    @Transactional
    public List<EpisodioDto> top5EpisodiosSerieTemporada(Long serieId, int temporada) {
        List<Episodio> topEpisodiosTemporadaDb = serieRepository.
                allEpisodiosSerieTemporadaFilterByNota(serieId,temporada)
                .orElseThrow(TituloNotPresentException::new);

        return topEpisodiosTemporadaDb
                .stream()
                .map(EpisodioDto::new)
                .limit(5)
                .toList();
    }

    public List<SerieDto> seriesByGenero(String genero) {
        Categoria categoria = Categoria.fromPortugues(genero);

        List<Serie> seriesPorCategoriaDb = serieRepository.findByGenero(categoria)
                .orElseThrow(TituloNotPresentException::new);

        return seriesPorCategoriaDb
                .stream()
                .map(SerieDto::new)
                .toList();
    }
}
