package com.project.screenmatch.infra.repository;

import com.project.screenmatch.domain.entities.Filme;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends TituloRepository<Filme> {


}
