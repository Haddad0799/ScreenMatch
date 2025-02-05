package com.project.screenmatch.service;

import com.project.screenmatch.dtos.FilmeDto;
import com.project.screenmatch.infra.exceptions.TituloNotFoundException;
import com.project.screenmatch.infra.exceptions.TituloNotPresentException;
import com.project.screenmatch.model.Categoria;
import com.project.screenmatch.model.Filme;
import com.project.screenmatch.repositorys.FilmeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilmeFilterService {
        private final FilmeRepository filmeRepository;

    public FilmeFilterService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Transactional
    public List<FilmeDto> allFilmes(){
        List<Filme> allFilmesDb = filmeRepository.findAll();

        if (allFilmesDb.isEmpty()){
            throw new TituloNotFoundException();
        }

        return allFilmesDb
                .stream()
                .map(FilmeDto::new)
                .toList();
    }

    @Transactional
    public List<FilmeDto> filmesTop5() {
        List<Filme> filmesTopDb = filmeRepository.findTop5ByOrderByNotaDesc()
                .orElseThrow(TituloNotPresentException::new);

        return filmesTopDb
                .stream()
                .map(FilmeDto::new)
                .limit(5)
                .toList();
    }

    @Transactional
    public List<FilmeDto> filmesLancamentos() {
        List<Filme> filmesLancamentosDb = filmeRepository.findByAno("2024")
                .orElseThrow(TituloNotPresentException::new);

        return filmesLancamentosDb
                .stream()
                .map(FilmeDto::new)
                .toList();
    }

    @Transactional
    public List<FilmeDto> filmesByGenero(String genero) {
        Categoria categoria = Categoria.fromPortugues(genero);

        List<Filme> filmesPorGeneroDb = filmeRepository.findByGenero(categoria)
                .orElseThrow(TituloNotPresentException::new);

        return filmesPorGeneroDb
                .stream()
                .map(FilmeDto::new)
                .toList();
    }
}
