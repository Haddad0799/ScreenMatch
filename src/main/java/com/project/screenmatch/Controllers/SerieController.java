package com.project.screenmatch.Controllers;

import com.project.screenmatch.dtos.SerieDto;
import com.project.screenmatch.service.BuscarTituloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/series")
public class SerieController {

    private final BuscarTituloService buscarTituloService;

    public SerieController(BuscarTituloService buscarTituloService) {
        this.buscarTituloService = buscarTituloService;
    }

    @GetMapping ("/{nomeSerie}")
    public SerieDto BuscarSerie(@PathVariable String nomeSerie){
        return  buscarTituloService.buscarTitulo(nomeSerie, SerieDto.class);
    }
}
