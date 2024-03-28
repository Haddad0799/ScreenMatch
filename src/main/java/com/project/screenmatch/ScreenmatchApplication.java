package com.project.screenmatch;

import com.project.screenmatch.dtos.DadoOmdbTitulo;
import com.project.screenmatch.infraestruct.ConsumirApiOmdb;
import com.project.screenmatch.model.Endereco;
import com.project.screenmatch.model.Serie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		ConsumirApiOmdb consumirApiOmdb = new ConsumirApiOmdb();

		String json = consumirApiOmdb.buscarDados(Endereco.montaEnderecoTitulo("the walking dead"));
		DadoOmdbTitulo dadoOmdbTitulo = consumirApiOmdb.converteDados(json, DadoOmdbTitulo.class);
		Serie serie = new Serie(dadoOmdbTitulo);
		System.out.println(serie);
	}
}
