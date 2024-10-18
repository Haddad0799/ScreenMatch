package com.project.screenmatch.Controllers;

import com.project.screenmatch.dtos.SerieDto;
import com.project.screenmatch.service.BuscarSerieService;
import com.project.screenmatch.service.SerieFilterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    private final BuscarSerieService buscarSerieService;
    private final SerieFilterService serieFilterService;

    public SerieController( BuscarSerieService buscarSerieService, SerieFilterService serieFilterService) {
        this.buscarSerieService = buscarSerieService;
        this.serieFilterService = serieFilterService;

    }

    @GetMapping ("/{nomeSerie}")
    public SerieDto BuscarSerie(@PathVariable String nomeSerie){
        return  buscarSerieService.buscarSerie(nomeSerie);
    }

    @GetMapping("/top5")
    public List<SerieDto> top5Filter(){
     return  serieFilterService.seriesTop5();
    }
}
