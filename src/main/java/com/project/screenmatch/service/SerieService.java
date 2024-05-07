package com.project.screenmatch.service;

import com.project.screenmatch.dtos.EpisodioDto;
import com.project.screenmatch.dtos.SerieDto;
import com.project.screenmatch.infraestruct.ConsumirApiOmdb;
import com.project.screenmatch.model.*;
import com.project.screenmatch.repositorys.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public SerieDto buscarSeriePorId(Long id) {
        Optional<Serie> serie = serieRepository.findById(id);

        if (serie.isPresent()){
            Serie serieEncontrada = serie.get();
            return  converterParaSerieDto(serieEncontrada);
        }
        return null;
    }

    public List<EpisodioDto> buscarTodosEpisodiosDaSerie(Long id) {
        Optional<Serie> serieOptional = serieRepository.findById(id);

        if (serieOptional.isPresent()) {
            Serie serieEncontrada = serieOptional.get();
            List<Episodio> episodios = serieEncontrada.getEpisodios();
            List<EpisodioDto> episodioDtos = new ArrayList<>();

            int temporadaAtual = 1; // Começamos na temporada 1

            for (Episodio episodio : episodios) {
                EpisodioDto episodioDto = new EpisodioDto(temporadaAtual, episodio.getTitulo(), episodio.getEpisodio(), episodio.getDataLancamento(), episodio.getNota());
                episodioDtos.add(episodioDto);

                // Se o próximo episódio não for da mesma temporada, incrementamos o número da temporada
                if (episodio != episodios.get(episodios.size() - 1) && episodio.getEpisodio() > episodios.get(episodios.indexOf(episodio) + 1).getEpisodio()) {
                    temporadaAtual++;
                }
            }

            return episodioDtos;
        } else {
            throw new RuntimeException("Série não encontrada com o ID: " + id);
        }
    }

    public List<EpisodioDto> buscarEpisodiosPorTemporada(Long id, Integer temporada) {
      List<EpisodioDto> episodioDtos = buscarTodosEpisodiosDaSerie(id);
      return episodioDtos.stream()
              .filter(e-> e.temporada().equals(temporada)).collect(Collectors.toList());
    }

    public List<SerieDto> buscarSeriePorCategoria(String categoria) {
        Categoria categoriaPortugues = Categoria.fromPortugues(categoria);
        List<Serie> seriesDeUmaCategoria = serieRepository.findByCategoria(categoriaPortugues);
        List<SerieDto> serieDtosCategoria = new ArrayList<>();

        for (Serie serie : seriesDeUmaCategoria) {
            SerieDto serieDto = converterParaSerieDto(serie);
            serieDtosCategoria.add(serieDto);
        }
        return serieDtosCategoria;
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
}
