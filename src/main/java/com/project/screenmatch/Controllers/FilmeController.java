package com.project.screenmatch.Controllers;

import com.project.screenmatch.dtos.FilmeDto;
import com.project.screenmatch.service.TituloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final TituloService tituloService;

    public FilmeController(TituloService tituloService) {
        this.tituloService = tituloService;
    }

    @GetMapping("/{nomeFilme}")
    public FilmeDto buscarFilme(@PathVariable String nomeFilme){
        return tituloService.buscarFilme(nomeFilme);
    }



}
