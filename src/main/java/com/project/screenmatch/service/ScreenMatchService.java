package com.project.screenmatch.service;

import com.project.screenmatch.dtos.DadoOmdbTitulo;
import com.project.screenmatch.infraestruct.ConsumirApiOmdb;
import com.project.screenmatch.model.Endereco;
import com.project.screenmatch.model.Episodio;
import com.project.screenmatch.model.Filme;
import com.project.screenmatch.model.Serie;
import com.project.screenmatch.repositorys.EpisodioRepository;
import com.project.screenmatch.repositorys.FilmeRepository;
import com.project.screenmatch.repositorys.SerieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Service
public class ScreenMatchService {

    private final ConsumirApiOmdb consumirApiOmdb;
    private final SerieRepository serieRepository;
    private final FilmeRepository filmeRepository;

    private final EpisodioRepository episodioRepository;

    Scanner lerDados = new Scanner(System.in);
    public ScreenMatchService(ConsumirApiOmdb consumirApiOmdb, SerieRepository serieRepository,
                              FilmeRepository filmeRepository, EpisodioRepository episodioRepository) {
        this.consumirApiOmdb = consumirApiOmdb;
        this.serieRepository = serieRepository;
        this.filmeRepository = filmeRepository;
        this.episodioRepository = episodioRepository;
    }

    public void menuInterativo() {
        boolean sair = false;

        while (!sair) {
            System.out.println("BEM VINDO AO SCREENMATCH!\n");
            System.out.println("Escolha uma opção do menu:");

            System.out.println(
                    """
                    1 - Buscar Título.
                    2 - Listar séries buscadas.
                    3 - Listar filmes buscados.
                    4 - Sair.
                    """
            );

            int opcaoUsuario;

            try {
                opcaoUsuario = lerDados.nextInt();
                lerDados.nextLine();

                switch (opcaoUsuario) {
                    case 1:
                        buscarTitulo();
                        break;
                    case 2:
                        listarSeries();
                        break;
                    case 3:
                        listarFilmes();
                        break;
                    case 4:
                        System.out.println("Saindo...");
                        sair = true;
                        break;
                    default:
                        System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
                }
            } catch (InputMismatchException exception) {
                lerDados.nextLine();
                System.out.println("Você digitou uma entrada inválida!");
            }
        }
    }

    @Transactional
    public void buscarTitulo() {
        String tituloPesquisado;

        System.out.println("Digite o título que deseja pesquisar:");

        tituloPesquisado = lerDados.nextLine();
        try {
            String json = consumirApiOmdb.buscarDados(Endereco.montaEnderecoTitulo(tituloPesquisado));
            DadoOmdbTitulo dadoOmdbTitulo = consumirApiOmdb.converteDados(json, DadoOmdbTitulo.class);


            if (dadoOmdbTitulo.tipo().equalsIgnoreCase("series")) {
                Serie serie = new Serie(dadoOmdbTitulo);
                if (serieRepository.existsByTitulo(serie.getTitulo())) {
                    Serie serieExistente = serieRepository.findByTitulo(serie.getTitulo());
                    System.out.println(serieExistente);
                } else {
                    salvarSerie(serie);
                    System.out.println(serie);
                }
            }

            if (dadoOmdbTitulo.tipo().equalsIgnoreCase("movie")) {
                Filme filme = new Filme(dadoOmdbTitulo);
                if (filmeRepository.existsByTitulo(filme.getTitulo())) {
                    Filme filmeExistente = filmeRepository.findByTitulo(filme.getTitulo());
                    System.out.println(filmeExistente);
                } else {
                    salvarFilme(filme);
                    System.out.println(filme);
                }
            }
        } catch (NullPointerException exception) {
            System.out.println("Não foi possível encontrar o título pesquisado.");
        }
    }


    public void salvarSerie(Serie serie) {
        serieRepository.save(serie);
    }

    public void salvarFilme(Filme filme) {
        filmeRepository.save(filme);
    }

    public void salvarEpisodio(Episodio episodio){
        episodioRepository.save(episodio);
    }

    public void listarFilmes() {
        List<Filme> filmesBuscados = filmeRepository.findAll();
        filmesBuscados.stream()
                .sorted(Comparator.comparing(Filme::getTitulo))
                .forEach(System.out::println);
    }

    public void listarSeries() {
        List<Serie> seriesBuscadas = serieRepository.findAll();
        seriesBuscadas.stream()
                .sorted(Comparator.comparing(Serie::getTitulo))
                .forEach(System.out::println);
    }

    private void buscarEpisodios() {


    }
}
