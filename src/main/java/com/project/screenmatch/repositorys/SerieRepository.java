package com.project.screenmatch.repositorys;

import com.project.screenmatch.model.Categoria;
import com.project.screenmatch.model.Episodio;
import com.project.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Serie s WHERE s.titulo = :titulo")
    boolean existsByTitulo(@Param("titulo") String titulo);

    Serie findByTitulo(String titulo);

    List<Serie> findTop5ByOrderByNotaDesc();

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:nomeEpisodio%")
    List<Episodio> findEpisodioPeloNome(String nomeEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.nota DESC LIMIT 5")
    List<Episodio> top5Episodios(Serie serie);

    @Query("SELECT s FROM Serie s " +
            "JOIN s.episodios e " +
            "GROUP BY s " +
            "ORDER BY MAX(e.dataLancamento) DESC LIMIT 5")
    List<Serie> seriesMaisRecentes();
    @Query("SELECT s FROM Serie s WHERE s.genero = :categoria")
    List<Serie> findByCategoria(Categoria categoria);


}

