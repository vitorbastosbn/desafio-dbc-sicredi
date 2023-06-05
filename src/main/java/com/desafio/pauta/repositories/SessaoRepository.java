package com.desafio.pauta.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desafio.pauta.entities.Sessao;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long>{
	
	@Query("SELECT s FROM Sessao s WHERE s.pauta.id = :idPauta")
	List<Sessao> pesquisarSessaoPorPauta(@Param("idPauta") Long idPauta);

}
