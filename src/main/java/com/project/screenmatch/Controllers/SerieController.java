package com.project.screenmatch.Controllers;

import com.project.screenmatch.dtos.SerieDto;
import com.project.screenmatch.service.TituloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/series")
public class SerieController {

    private final TituloService tituloService;

    public SerieController(TituloService tituloService) {
        this.tituloService = tituloService;
    }

    @GetMapping ("/{nomeSerie}")
    public SerieDto BuscarSerie(@PathVariable String nomeSerie){
        return  tituloService.buscarSerie(nomeSerie);
    }
}
