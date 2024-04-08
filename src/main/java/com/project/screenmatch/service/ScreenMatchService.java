package com.project.screenmatch.service;

import com.project.screenmatch.dtos.DadoOmdbTitulo;
import com.project.screenmatch.infraestruct.ConsumirApiOmdb;
import com.project.screenmatch.model.Endereco;
import com.project.screenmatch.model.Filme;
import com.project.screenmatch.model.Serie;
import com.project.screenmatch.repositorys.FilmeRepository;
import com.project.screenmatch.repositorys.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Service
public class ScreenMatchService {

    private final ConsumirApiOmdb consumirApiOmdb;
    private final SerieRepository serieRepository;

    private final FilmeRepository filmeRepository;

    private List<Serie> seriesBuscadas;
    private List<Filme> filmesBuscados;

    Scanner lerDados = new Scanner(System.in);
    public ScreenMatchService(ConsumirApiOmdb consumirApiOmdb, SerieRepository serieRepository, FilmeRepository filmeRepository) {
        this.consumirApiOmdb = consumirApiOmdb;
        this.serieRepository = serieRepository;
        this.filmeRepository = filmeRepository;
    }

    public void menuInterativo() {

        boolean sair = false;

        while(!sair) {

            System.out.println("BEM VINDO AO SCREENMATCH!\n");
            System.out.println("Escolha uma opção do menu:");
            int opcaousuario = 0;

            System.out.println(
                    """
                    1 - Buscar Titulo.
                    2 - Listar series buscadas.
                    3 - Listar filmes buscados.
                    4 - sair.       \s
                            """

            );
            try {
                opcaousuario = lerDados.nextInt();
                lerDados.nextLine();

                switch (opcaousuario){
                    case 1:buscarTitulo();
                    break;
                    case 2: listarSeries();
                    break;
                    case 3: listarFilmes();
                    break;
                    case 4: sair = true;
                        System.out.println("Saindo...");
                        break;
                }
            } catch(InputMismatchException exception ) {
                lerDados.nextLine();
                System.out.println("Você digitou Uma entrada inválida!");
            }
        }
    }

    public void buscarTitulo(){
        String tituloPesquisado;

        System.out.println("Digite o titulo que deseja pesquisar:");

        tituloPesquisado = lerDados.nextLine();
        String json = consumirApiOmdb.buscarDados(Endereco.montaEnderecoTitulo(tituloPesquisado));
        DadoOmdbTitulo dadoOmdbTitulo = consumirApiOmdb.converteDados(json , DadoOmdbTitulo.class);

        if(dadoOmdbTitulo.tipo().equalsIgnoreCase("series")) {
            Serie serie = new Serie(dadoOmdbTitulo);
            salvarSerie(serie);
            System.out.println(serie);
        }

        if(dadoOmdbTitulo.tipo().equalsIgnoreCase("movie")) {
            Filme filme = new Filme(dadoOmdbTitulo);
            salvarFilme(filme);
            System.out.println(filme);
        }
    }

    public void salvarSerie(Serie serie) {
        serieRepository.save(serie);
    }

    public void salvarFilme(Filme filme) {
        filmeRepository.save(filme);
    }

    public void listarFilmes() {
        filmesBuscados = filmeRepository.findAll();
        filmesBuscados.stream()
                .sorted(Comparator.comparing(Filme::getTitulo))
                .forEach(System.out::println);
    }

    public void listarSeries() {
        seriesBuscadas = serieRepository.findAll();
        seriesBuscadas.stream()
                .sorted(Comparator.comparing(Serie::getTitulo))
                .forEach(System.out::println);
    }
}
