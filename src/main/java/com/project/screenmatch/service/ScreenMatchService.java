package com.project.screenmatch.service;

import com.project.screenmatch.dtos.DadoOmdbTemporada;
import com.project.screenmatch.dtos.DadoOmdbTitulo;
import com.project.screenmatch.infraestruct.ConsumirApiOmdb;
import com.project.screenmatch.model.Endereco;
import com.project.screenmatch.model.Episodio;
import com.project.screenmatch.model.Filme;
import com.project.screenmatch.model.Serie;
import com.project.screenmatch.repositorys.FilmeRepository;
import com.project.screenmatch.repositorys.SerieRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ScreenMatchService {

    private final ConsumirApiOmdb consumirApiOmdb;
    private final SerieRepository serieRepository;
    private final FilmeRepository filmeRepository;



    @Getter
    private List<Serie> seriesBuscadas = new ArrayList<>();

    Scanner lerDados = new Scanner(System.in);
    public ScreenMatchService(ConsumirApiOmdb consumirApiOmdb, SerieRepository serieRepository,
                              FilmeRepository filmeRepository) {
        this.consumirApiOmdb = consumirApiOmdb;
        this.serieRepository = serieRepository;
        this.filmeRepository = filmeRepository;
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
                    4 - Buscar Episódios.
                    5 - Sair.
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
                    case 4:buscarEpisodios();
                    break;
                    case 5:
                        System.out.println("Saindo...");
                        sair = true;
                        break;
                    default:
                        System.out.println("Opção inválida! Por favor, escolha uma opção presente no menu.");
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


    public void listarFilmes() {
        List<Filme> filmesBuscados = filmeRepository.findAll();
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

    @Transactional
    private void buscarEpisodios() {
        seriesBuscadas = serieRepository.findAll();
        System.out.println("Digite a série que deseja saber os episódios:");
        String seriePesquisada = lerDados.nextLine();

        Optional<Serie> serie = getSeriesBuscadas().stream()
                .filter(s -> s.getTitulo().toLowerCase().contains(seriePesquisada.toLowerCase()))
                .findFirst();


        if (serie.isPresent()) {
            Serie serieEncontrada = serie.get();

            List<Episodio> episodios = new ArrayList<>();

            if (serieEncontrada.getEpisodios().isEmpty()){
                for (int i = 1; i <= serieEncontrada.getTemporadas(); i++) {
                    String json = consumirApiOmdb.buscarDados(Endereco.montaEnderecoTemporada(serieEncontrada.getTitulo(), i));
                    DadoOmdbTemporada dadoOmdbTemporada = consumirApiOmdb.converteDados(json, DadoOmdbTemporada.class);

                    episodios.addAll(dadoOmdbTemporada.episodios().stream()
                            .map(Episodio::new)
                            .toList());

                }
                serieEncontrada.setEpisodios(episodios);
                episodios.forEach(System.out::println);
                serieRepository.save(serieEncontrada);
            } else {
                serieEncontrada.getEpisodios().forEach(System.out::println);
            }
        } else {

            try{
                String json = consumirApiOmdb.buscarDados(Endereco.montaEnderecoTitulo(seriePesquisada));
                DadoOmdbTitulo dadoOmdbTitulo = consumirApiOmdb.converteDados(json,DadoOmdbTitulo.class);
                Serie serieOmdb = new Serie(dadoOmdbTitulo);
                serieRepository.save(serieOmdb);

                for (int i = 1; i <= serieOmdb.getTemporadas(); i++) {
                    json = consumirApiOmdb.buscarDados(Endereco.montaEnderecoTemporada(serieOmdb.getTitulo(), i));
                    DadoOmdbTemporada dadoOmdbTemporada = consumirApiOmdb.converteDados(json, DadoOmdbTemporada.class);

                    List<Episodio> episodios = new ArrayList<>(dadoOmdbTemporada.episodios().stream()
                            .map(Episodio::new)
                            .toList());

                    serieOmdb.setEpisodios(episodios);
                    episodios.forEach(System.out::println);
                    serieRepository.save(serieOmdb);
                }
            } catch (NullPointerException exception) {
                System.out.println("Não foi possível encontrar a série buscada!");
            }

        }

    }

}
