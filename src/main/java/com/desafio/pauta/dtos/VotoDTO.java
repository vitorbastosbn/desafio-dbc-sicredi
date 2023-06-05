package com.desafio.pauta.dtos;

import lombok.Data;

@Data
public class VotoDTO {
	
	private Long idAssociado;
	private Long idSessao;
	private Character voto;

}
