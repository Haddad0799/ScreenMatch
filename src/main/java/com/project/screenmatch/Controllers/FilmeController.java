package com.project.screenmatch.Controllers;

import com.project.screenmatch.dtos.FilmeDto;
import com.project.screenmatch.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilmeController {

    @Autowired
    FilmeService filmeService;

    @GetMapping("/filmes")
    public List<FilmeDto> getFilmes(){
        return filmeService.listarFilmes();
    }

    @GetMapping("/filmes/top5")
    public List<FilmeDto> getTop5Filmes() {
        return filmeService.top5Filmes();
    }

    @GetMapping("/filmes/lancamentos")
    public List<FilmeDto> getFilmesLancamentos(){return filmeService.filmesLancamentos();}

    @GetMapping("/filmes/buscarFilme")
    public FilmeDto buscarFilme(){return filmeService.buscarFilme("maze runner");}

}
