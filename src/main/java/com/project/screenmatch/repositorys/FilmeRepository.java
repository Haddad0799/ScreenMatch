package com.project.screenmatch.repositorys;

import com.project.screenmatch.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Filme f WHERE f.titulo = :titulo")
    boolean existsByTitulo(@Param("titulo") String titulo);

    Filme findByTitulo(String titulo);

    List<Filme> findTop5ByOrderByNotaDesc();
}
