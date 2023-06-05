package com.desafio.pauta.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_VOTO")
public class Voto {

	@EmbeddedId
	private VotoId id;

	@Column(name = "voto")
	private Character voto;
	
}
