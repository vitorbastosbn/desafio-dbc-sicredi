package com.desafio.pauta.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum SimNaoEnum {

	SIM('S'), NAO('N');

	SimNaoEnum(Character c) {
		this.valor = c;
	}

	private Character valor;
	
	public SimNaoEnum getEnum(Character valor) {
		return SimNaoEnum.valueOf(valor.toString());
	}

}
