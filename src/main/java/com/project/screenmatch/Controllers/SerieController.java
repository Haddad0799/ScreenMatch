package com.project.screenmatch.Controllers;

import com.project.screenmatch.dto.EpisodioDto;
import com.project.screenmatch.dto.SerieDto;
import com.project.screenmatch.service.BuscarSerieService;
import com.project.screenmatch.service.SerieFilterService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<SerieDto> BuscarSerie(@PathVariable String nomeSerie){
        return ResponseEntity.ok().body(buscarSerieService.buscarSerie(nomeSerie));
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<SerieDto>> seriesBygenero(@PathVariable String genero) {
        return ResponseEntity.ok().body(serieFilterService.seriesByGenero(genero));
    }

    @GetMapping("/top5")
    public ResponseEntity<List<SerieDto>> top5Series(){
     return ResponseEntity.ok().body(serieFilterService.seriesTop5());
    }

    @GetMapping("lancamentos")
    public ResponseEntity<List<SerieDto>> seriesLancamentos() {
        return ResponseEntity.ok().body(serieFilterService.seriesLancamentos());
    }

    @GetMapping("/{id}/episodios")
    public ResponseEntity<List<EpisodioDto>> serieAllEpisodios(@PathVariable Long id) {
        return ResponseEntity.ok().body(serieFilterService.allEpisodiosSerie(id));
    }

    @GetMapping("{id}/episodios/{temporada}")
    public ResponseEntity<List<EpisodioDto>> serieAllEpisodiosTemporada(@PathVariable Long id, @PathVariable int temporada){
        return ResponseEntity.ok().body(serieFilterService.allEpisodiosSerieTemporada(id, temporada));
    }

    @GetMapping("/{id}/episodios/top5")
    public ResponseEntity<List<EpisodioDto>> top5EpisodiosDaSerie(@PathVariable Long id) {
        return ResponseEntity.ok().body(serieFilterService.top5EpisodiosSerie(id));
    }

    @GetMapping("{id}/episodios/{temporada}/top5")
    public ResponseEntity<List<EpisodioDto>> top5EpisodiosTemporada(@PathVariable Long id, @PathVariable int temporada){
        return ResponseEntity.ok().body(serieFilterService.top5EpisodiosSerieTemporada(id,temporada));
    }

}
