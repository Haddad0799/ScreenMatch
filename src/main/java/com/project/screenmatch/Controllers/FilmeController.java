package com.project.screenmatch.Controllers;

import com.project.screenmatch.dtos.FilmeDto;
import com.project.screenmatch.service.BuscarFilmeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final BuscarFilmeService buscarFilmeService;

    public FilmeController(BuscarFilmeService buscarFilmeService) {
        this.buscarFilmeService = buscarFilmeService;
    }

    @GetMapping("/{nomeFilme}")
    public FilmeDto buscarFilme(@PathVariable String nomeFilme){
        return buscarFilmeService.buscarFilme(nomeFilme);
    }



}
