package com.project.screenmatch.repositorys;

import com.project.screenmatch.model.Episodio;
import com.project.screenmatch.model.Serie;
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
    Optional<List<Episodio>> topEpisodios(@Param("id") Long id);

    @Query("SELECT e FROM Episodio e WHERE e.serie.id = :id")
    Optional<List<Episodio>> allEpisodios(@Param("id") Long id);

    @Query("SELECT e " +
            "FROM Episodio e " +
            "WHERE e.serie.id = :id " +
            "AND e.temporada = :temporada")
    Optional<List<Episodio>> episodiosFindAllByTemporada(@Param("id") Long id, @Param("temporada") int temporada);


}

