package com.project.screenmatch.infra.repository;

import com.project.screenmatch.domain.entities.Episodio;
import com.project.screenmatch.domain.entities.Serie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SerieRepository extends TituloRepository<Serie> {


    @Query("""
       SELECT e\s
       FROM Episodio e\s
       WHERE e.serie.id = :id
       AND e.nota >= 8.0
       ORDER BY e.nota DESC
      \s""")
    Optional<List<Episodio>> topEpisodiosSerie(@Param("id") Long id);

    @Query("SELECT e FROM Episodio e WHERE e.serie.id = :id")
    Optional<List<Episodio>> allEpisodiosSerie(@Param("id") Long id);

    @Query("SELECT e " +
            "FROM Episodio e " +
            "WHERE e.serie.id = :id " +
            "AND e.temporada = :temporada")
    Optional<List<Episodio>> allEpisodiosSerieTemporada(@Param("id") Long id, @Param("temporada") int temporada);


    @Query("SELECT e " +
            "FROM Episodio e " +
            "WHERE e.serie.id = :id " +
            "AND e.temporada = :temporada ORDER BY e.nota DESC")
    Optional<List<Episodio>> allEpisodiosSerieTemporadaFilterByNota(@Param("id") Long id, @Param("temporada") int temporada);

}

