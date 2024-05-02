package com.project.screenmatch.service;

import com.project.screenmatch.dtos.SerieDto;
import com.project.screenmatch.model.Serie;
import com.project.screenmatch.repositorys.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {
    @Autowired
    SerieRepository serieRepository;

    public List<SerieDto> listarSeries() {
        return serieRepository.findAll().stream()
                .map(this::converterParaSerieDto)
                .collect(Collectors.toList());
    }

    public List<SerieDto> top5Series() {
        return serieRepository.findTop5ByOrderByNotaDesc().stream()
                .map(this::converterParaSerieDto)
                .collect(Collectors.toList());
    }

    public List<SerieDto> seriesLancamentos() {
        return serieRepository.seriesMaisRecentes().stream()
                .map(this::converterParaSerieDto)
                .collect(Collectors.toList());
    }

    private SerieDto converterParaSerieDto(Serie serie) {
        return new SerieDto(
                serie.getTitulo(),
                serie.getTemporadas(),
                serie.getAno(),
                serie.getDataDeLancamento(),
                serie.getTempoDeDuracao(),
                serie.getGenero(),
                serie.getDiretor(),
                serie.getRoteirista(),
                serie.getAtores(),
                serie.getSinopse(),
                serie.getIdioma(),
                serie.getPais(),
                serie.getPremiacoes(),
                serie.getUrlDaImagem(),
                serie.getNota(),
                serie.getVotos()
        );
    }

}
