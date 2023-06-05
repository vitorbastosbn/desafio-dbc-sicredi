package com.desafio.pauta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.pauta.entities.Associado;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

}
