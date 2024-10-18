package com.project.screenmatch.repositorys;

import com.project.screenmatch.model.Categoria;
import com.project.screenmatch.model.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface TituloRepository<T extends Titulo> extends JpaRepository<T, Long> {
    // Métodos comuns para Filme e Serie

   Optional<T> findByTituloContainingIgnoreCase(String titulo);
    List<T> findByGenero(Categoria genero);
    List<T> findByNotaGreaterThanEqual(Double nota);
    List<T> findTop5ByOrderByNotaDesc();
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Filme f WHERE f.titulo = :titulo")
    boolean existsByTitulo(@Param("titulo") String titulo);

    @Query("SELECT f FROM Filme f WHERE f.dataDeLancamento LIKE %:ano%")
    List<T> findByAno(@Param("ano") String ano);

}
