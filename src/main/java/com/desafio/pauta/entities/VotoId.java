package com.desafio.pauta.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class VotoId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id_tb_sessao")
	private Long idSessao;

	@Column(name = "id_tb_associado")
	private Long idAssociado;

}
