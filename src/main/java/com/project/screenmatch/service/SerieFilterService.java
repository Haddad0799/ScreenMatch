package com.project.screenmatch.service;

import com.project.screenmatch.dtos.EpisodioDto;
import com.project.screenmatch.dtos.SerieDto;
import com.project.screenmatch.infra.exceptions.TituloNotFoundException;
import com.project.screenmatch.infra.exceptions.TituloNotPresentException;
import com.project.screenmatch.model.Episodio;
import com.project.screenmatch.model.Serie;
import com.project.screenmatch.repositorys.SerieRepository;
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

        return seriesLancamentosDb.stream().map(SerieDto::new).toList();
    }

    @Transactional
    public List<EpisodioDto> allEpisodios(Long id) {
        List<Episodio> episodiosDb = serieRepository.allEpisodios(id)
                .orElseThrow(TituloNotFoundException::new);

        return episodiosDb.stream().map(EpisodioDto::new).toList();

    }
    @Transactional
    public List<EpisodioDto> top5Episodios(Long id) {
        List<Episodio> topEpisodiosDb = serieRepository.topEpisodios(id)
                .orElseThrow(TituloNotPresentException::new);

        return topEpisodiosDb.stream().map(EpisodioDto::new).limit(5).toList();
    }

    @Transactional
    public List<EpisodioDto> episodiosTemporada(Long id, int temporada) {
        List<Episodio> topEpisodiosTemporadaDb = serieRepository
                .episodiosFindAllByTemporada(id,temporada)
                .orElseThrow(TituloNotFoundException::new);

        return topEpisodiosTemporadaDb.stream().map(EpisodioDto::new).toList();
    }

}
