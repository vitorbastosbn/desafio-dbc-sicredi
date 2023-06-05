package com.desafio.pauta.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.desafio.pauta.dtos.AssociadoDTO;
import com.desafio.pauta.entities.Associado;
import com.desafio.pauta.repositories.AssociadoRepository;

@Service
public class AssociadoService {

	@Autowired
	private AssociadoRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	public AssociadoDTO criar(AssociadoDTO dto) {
		var associado = this.modelMapper.map(dto, Associado.class);
		var associadoSalvo = this.repository.save(associado);
		var associadoRetorno = this.modelMapper.map(associadoSalvo, AssociadoDTO.class);

		return associadoRetorno;
	}

	public AssociadoDTO pesquisarPorId(Long id) {
		var associado = this.repository.findById(id);

		if (!associado.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		var associadoDTO = this.modelMapper.map(associado.get(), AssociadoDTO.class);
		return associadoDTO;
	}

	public List<AssociadoDTO> pesquisarTodos() {
		var associados = this.repository.findAll();
		var associadosDTO = associados.stream().map(associado -> this.modelMapper.map(associado, AssociadoDTO.class))
				.collect(Collectors.toList());
		return associadosDTO;
	}

}
