package com.project.screenmatch.service;

import com.project.screenmatch.dtos.SerieDto;
import com.project.screenmatch.infraestruct.ConsumirApiOmdb;
import com.project.screenmatch.model.DadoOmdbTitulo;
import com.project.screenmatch.model.Endereco;
import com.project.screenmatch.model.Serie;
import com.project.screenmatch.repositorys.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {
    @Autowired
    SerieRepository serieRepository;

    @Autowired
    ConsumirApiOmdb consumirApiOmdb;

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

    public SerieDto buscarSerie(String nomeSerie) {
        List <Serie> seriesSalvas = serieRepository.findAll();
        Optional<Serie> serie = seriesSalvas.stream()
                .filter(s-> s.getTitulo().toLowerCase().contains(nomeSerie.toLowerCase()))
                .findFirst();

        if(serie.isPresent()){
            Serie serieEncontrada = serie.get();
            return converterParaSerieDto(serieEncontrada);
        } else{
            String json = consumirApiOmdb.buscarDados(Endereco.montaEnderecoTitulo(nomeSerie));
            DadoOmdbTitulo dadoOmdbTitulo = consumirApiOmdb.converteDados(json, DadoOmdbTitulo.class);
            Serie serieOmdb = new Serie(dadoOmdbTitulo);
            serieRepository.save(serieOmdb);
            return converterParaSerieDto(serieOmdb);
        }
    }

    private SerieDto converterParaSerieDto(Serie serie) {
        return new SerieDto(
                serie.getIdScreenmatch(),
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

    public SerieDto buscarSeriePorId(Long id) {
        Optional<Serie> serie = serieRepository.findById(id);

        if (serie.isPresent()){
            Serie serieEncontrada = serie.get();
            return  converterParaSerieDto(serieEncontrada);
        }
        return null;
    }
}
