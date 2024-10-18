package com.project.screenmatch.service;

import com.project.screenmatch.dtos.DadoOmdbTitulo;
import com.project.screenmatch.dtos.FilmeDto;
import com.project.screenmatch.infra.exceptions.TituloNotFoundException;
import com.project.screenmatch.integration.IntegracaoApiOmdbService;
import com.project.screenmatch.model.Filme;
import com.project.screenmatch.repositorys.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarFilmeService {
    private final FilmeRepository filmeRepository;
    private final BuscarTituloOmdbService buscarTituloOmdbService;

    public BuscarFilmeService(FilmeRepository filmeRepository, IntegracaoApiOmdbService integracaoApiOmdbService, BuscarTituloOmdbService buscarTituloOmdbService) {
        this.filmeRepository = filmeRepository;
        this.buscarTituloOmdbService = buscarTituloOmdbService;
    }

    public FilmeDto buscarFilme(String tituloPesquisado) {
        Optional<Filme> filmeDbOpt = filmeRepository.findByTituloContainingIgnoreCase(tituloPesquisado);

        if (filmeDbOpt.isEmpty()) {
            DadoOmdbTitulo dadoOmdbTitulo = buscarTituloOmdbService.buscarTituloOmdb(tituloPesquisado);
            if(dadoOmdbTitulo.tipo().equalsIgnoreCase("movie")) {
                Filme filmeOmdb = new Filme(dadoOmdbTitulo);
                filmeRepository.save(filmeOmdb);
                return new FilmeDto(filmeOmdb);
            }
        }

        return filmeDbOpt.map(FilmeDto::new).orElseThrow(TituloNotFoundException::new);
    }
}
