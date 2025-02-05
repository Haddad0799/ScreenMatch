package com.project.screenmatch.infra.repository;

import com.project.screenmatch.domain.model.Categoria;
import com.project.screenmatch.domain.entities.Titulo;
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
   Optional<List<T>> findByGenero(@Param("genero") Categoria genero);

    Optional<List<T>>findTop5ByOrderByNotaDesc();

    @Query("SELECT t FROM Titulo t WHERE t.dataDeLancamento LIKE %:ano%")
   Optional<List<T>>findByAno(@Param("ano") String ano);

}
