package com.desafio.pauta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.pauta.entities.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long>{

}
