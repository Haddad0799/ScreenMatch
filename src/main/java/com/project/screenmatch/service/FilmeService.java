package com.project.screenmatch.service;

import com.project.screenmatch.dtos.FilmeDto;
import com.project.screenmatch.infraestruct.ConsumirApiOmdb;
import com.project.screenmatch.model.DadoOmdbTitulo;
import com.project.screenmatch.model.Endereco;
import com.project.screenmatch.model.Filme;
import com.project.screenmatch.repositorys.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    ConsumirApiOmdb consumirApiOmdb;

    public List<FilmeDto> listarFilmes() {
        return filmeRepository.findAll().stream()
                .map(this::converterParaFilmeDto)
                .collect(Collectors.toList());
    }

    public List<FilmeDto> top5Filmes() {
        return filmeRepository.findTop5ByOrderByNotaDesc().stream()
                .map(this::converterParaFilmeDto)
                .collect(Collectors.toList());
    }

    public List<FilmeDto> filmesLancamentos() {
        return filmeRepository.filmesPorAno("2024").stream()
                .map(this::converterParaFilmeDto)
                .collect(Collectors.toList());
    }

    public FilmeDto buscarFilme(String nomeDoFilme){
        List<Filme> filmesSalvos = filmeRepository.findAll();

        Optional<Filme> filme = filmesSalvos.stream()
                .filter(f-> f.getTitulo().toLowerCase().contains(nomeDoFilme.toLowerCase()))
                .findFirst();

        if (filme.isPresent()){
            Filme filmeEncontrado = filme.get();
            return converterParaFilmeDto(filmeEncontrado);
        } else {
            String json = consumirApiOmdb.buscarDados(Endereco.montaEnderecoTitulo(nomeDoFilme));
            DadoOmdbTitulo dadoOmdbTitulo = consumirApiOmdb.converteDados(json,DadoOmdbTitulo.class);
            Filme filmeOmdb = new Filme(dadoOmdbTitulo);
            filmeRepository.save(filmeOmdb);
            return converterParaFilmeDto(filmeOmdb);
        }
    }

    public FilmeDto buscarPorId(Long id) {
        Optional<Filme> filme = filmeRepository.findById(id);

        if (filme.isPresent()){
         Filme filmeEncontrado = filme.get();
         return converterParaFilmeDto(filmeEncontrado);
        }
        return null;
    }


    private FilmeDto converterParaFilmeDto(Filme filme) {
        return new FilmeDto(
                filme.getIdScreenmatch(),
                filme.getTitulo(),
                filme.getAno(),
                filme.getDataDeLancamento(),
                filme.getTempoDeDuracao(),
                filme.getGenero(),
                filme.getDiretor(),
                filme.getRoteirista(),
                filme.getAtores(),
                filme.getSinopse(),
                filme.getIdioma(),
                filme.getPais(),
                filme.getPremiacoes(),
                filme.getUrlDaImagem(),
                filme.getNota(),
                filme.getVotos()
        );
    }

}

