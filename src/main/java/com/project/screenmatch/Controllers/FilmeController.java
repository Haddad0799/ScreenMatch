package com.project.screenmatch.Controllers;

import com.project.screenmatch.dtos.FilmeDto;
import com.project.screenmatch.service.BuscarFilmeService;
import com.project.screenmatch.service.FilmeFilterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final BuscarFilmeService buscarFilmeService;
    private final FilmeFilterService filmeFilterService;

    public FilmeController(BuscarFilmeService buscarFilmeService, FilmeFilterService filmeFilterService) {
        this.buscarFilmeService = buscarFilmeService;
        this.filmeFilterService = filmeFilterService;
    }

    @GetMapping("/{nomeFilme}")
    public ResponseEntity<FilmeDto> buscarFilme(@PathVariable String nomeFilme){
        return ResponseEntity.ok()
                .body(buscarFilmeService.buscarFilme(nomeFilme));
    }

    @GetMapping("/top5")
    public ResponseEntity<List<FilmeDto>> filmesTop5(){
        return ResponseEntity.ok()
                .body(filmeFilterService.filmesTop5());
    }

    @GetMapping("/lancamentos")
    public ResponseEntity<List<FilmeDto>> filmesLancamentos(){
        return ResponseEntity.ok()
                .body(filmeFilterService.filmesLancamentos());
    }

    @GetMapping("genero/{genero}")
    public ResponseEntity<List<FilmeDto>> filmesByGenero(@PathVariable String genero){
        return ResponseEntity.ok().body(filmeFilterService.filmesByGenero(genero));
    }



}
