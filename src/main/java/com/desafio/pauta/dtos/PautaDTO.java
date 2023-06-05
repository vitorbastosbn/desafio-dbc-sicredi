package com.desafio.pauta.dtos;

import com.desafio.pauta.enums.SituacaoEnum;

import lombok.Data;

@Data
public class PautaDTO {
	
	private Long id;
	private String descricao;
	private SituacaoEnum resultado;

}
