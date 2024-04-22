package com.project.screenmatch.repositorys;

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

}
