package com.project.screenmatch.service;

import com.project.screenmatch.dtos.SerieDto;
import com.project.screenmatch.model.Serie;
import com.project.screenmatch.repositorys.SerieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SerieFilterService {

    private final SerieRepository serieRepository;

    public SerieFilterService( SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    @Transactional
    public List<SerieDto> seriesTop5(){
        Optional<List<Serie>>  seriesTop5Db = serieRepository.findTop5ByOrderByNotaDesc();

        if(seriesTop5Db.isEmpty()) {
            throw new InternalError();
        }

        List<Serie> seriesTop5 = seriesTop5Db.get();

        return seriesTop5.stream().map(SerieDto::new).toList();
    }
}
