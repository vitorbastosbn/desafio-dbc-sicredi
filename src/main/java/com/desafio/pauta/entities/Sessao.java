package com.desafio.pauta.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_SESSAO")
public class Sessao {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "dt_inicio", nullable = true)
	private LocalDateTime dtInicio;
	
	@Column(name = "dt_fim", nullable = true)
	private LocalDateTime dtFim;
	
	@Column(name = "nr_duracao", nullable = true)
	private Long duracaoEmMinutos;

	@ManyToOne
	@JoinColumn(name = "id_pauta", nullable = false)
	private Pauta pauta;

	@ManyToMany
	@JoinTable(
			name = "TB_VOTO",
			joinColumns = { @JoinColumn(name = "id_tb_sessao") },
			inverseJoinColumns = { @JoinColumn(name = "id_tb_associado") }
			)
	private List<Associado> associados;
}
