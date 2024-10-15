package com.project.screenmatch.Controllers;

import com.project.screenmatch.dtos.FilmeDto;
import com.project.screenmatch.service.BuscarTituloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final BuscarTituloService buscarTituloService;

    public FilmeController(BuscarTituloService buscarTituloService) {
        this.buscarTituloService = buscarTituloService;
    }

    @GetMapping("/{nomeFilme}")
    public FilmeDto buscarFilme(@PathVariable String nomeFilme){
        return buscarTituloService.buscarTitulo(nomeFilme, FilmeDto.class);
    }



}
