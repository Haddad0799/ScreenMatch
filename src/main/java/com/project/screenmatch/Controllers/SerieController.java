package com.project.screenmatch.Controllers;

import com.project.screenmatch.dtos.SerieDto;
import com.project.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SerieController {

    @Autowired
    SerieService serieService;

    @GetMapping("/series")
   public List<SerieDto> getSeries(){
        return serieService.listarSeries();
    }

    @GetMapping("/series/top5")
    public List<SerieDto> getTop5Series() {
        return serieService.top5Series();
    }

    @GetMapping("/series/lancamentos")
    public List<SerieDto> getSeriesLancamentos(){return serieService.seriesLancamentos();}
}
