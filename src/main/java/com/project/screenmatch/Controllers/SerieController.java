package com.project.screenmatch.Controllers;

import com.project.screenmatch.dtos.EpisodioDto;
import com.project.screenmatch.dtos.SerieDto;
import com.project.screenmatch.service.SerieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/series")
public class SerieController {

    final
    SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping()
   public List<SerieDto> getSeries(){
        return serieService.listarSeries();
    }

    @GetMapping("/top5")
    public List<SerieDto> getTop5Series() {
        return serieService.top5Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDto> getSeriesLancamentos(){return serieService.seriesLancamentos();}

    @GetMapping ("/{nomeSerie}")
    public SerieDto getserieBuscada(@PathVariable String nomeSerie){
        return  serieService.buscarSerie(nomeSerie);
    }

    @GetMapping("/{id}")
    public SerieDto getSerieById(@PathVariable Long id) {
        return serieService.buscarSeriePorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDto> TemporadasDaSerie(@PathVariable Long id){
        return serieService.buscarTodosEpisodiosDaSerie(id);
    }

    @GetMapping("/{id}/temporadas/{temporada}")
    public List<EpisodioDto> episodiosPorTemporada(@PathVariable Long id, @PathVariable Integer temporada){
        return serieService.buscarEpisodiosPorTemporada(id,temporada);
    }

    @GetMapping("/categoria/{categoria}")
    public List<SerieDto> seriesPorCategoria(@PathVariable String categoria){
        return serieService.buscarSeriePorCategoria(categoria);
    }

}
