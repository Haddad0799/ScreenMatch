package com.project.screenmatch.service;

import com.project.screenmatch.dtos.DadoOmdbTitulo;
import com.project.screenmatch.infraestruct.ConsumirApiOmdb;
import com.project.screenmatch.model.Endereco;
import com.project.screenmatch.model.Filme;
import com.project.screenmatch.model.Serie;
import com.project.screenmatch.repositorys.FilmeRepository;
import com.project.screenmatch.repositorys.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class ScreenMatchService {

    private final ConsumirApiOmdb consumirApiOmdb;
    private final SerieRepository serieRepository;

    private final FilmeRepository filmeRepository;
    public ScreenMatchService(ConsumirApiOmdb consumirApiOmdb, SerieRepository serieRepository, FilmeRepository filmeRepository) {
        this.consumirApiOmdb = consumirApiOmdb;
        this.serieRepository = serieRepository;
        this.filmeRepository = filmeRepository;
    }

    public void menuInterativo() {

        boolean sair = false;

        while(!sair) {
            Scanner lerOpcaoMenu = new Scanner(System.in);

            System.out.println("BEM VINDO AO SCREENMATCH!\n");
            System.out.println("Escolha uma opção do menu:");
            int opcaousuario = 0;

            System.out.println(
                    """
                    1 - Buscar Titulo.
                    2 - sair.       \s
                            """

            );
            try {
                opcaousuario = lerOpcaoMenu.nextInt();

                switch (opcaousuario){
                    case 1:buscarTitulo();
                    break;
                    case 2: sair = true;
                        System.out.println("Saindo...");
                        break;
                }
            } catch(InputMismatchException exception ) {
                lerOpcaoMenu.nextLine();
                System.out.println("Você digitou Uma entrada inválida!");
            }
        }
    }

    public void buscarTitulo(){
        Scanner lerTituloDeBusca = new Scanner(System.in);
        String tituloPesquisado;

        System.out.println("Digite o titulo que deseja pesquisar:");

        tituloPesquisado = lerTituloDeBusca.nextLine();
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
}
