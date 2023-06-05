package com.desafio.pauta.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desafio.pauta.entities.Voto;
import com.desafio.pauta.entities.VotoId;

@Repository
public interface VotoRepository extends JpaRepository<Voto, VotoId>{
	
	@Query("SELECT COUNT(v.voto) FROM Voto v WHERE v.id.idSessao IN :idSessoesPauta AND v.voto = :voto")
	Long contagemVotosPorPauta(@Param("idSessoesPauta") List<Long> idSessoesPauta, @Param("voto") Character voto);

}
