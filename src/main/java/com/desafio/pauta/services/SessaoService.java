package com.desafio.pauta.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.desafio.pauta.dtos.SessaoDTO;
import com.desafio.pauta.entities.Sessao;
import com.desafio.pauta.repositories.SessaoRepository;

@Service
public class SessaoService {

	@Autowired
	private SessaoRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	public SessaoDTO criar(SessaoDTO dto) {
		var sessao = this.modelMapper.map(dto, Sessao.class);
		if (sessao.getDuracaoEmMinutos() == null || sessao.getDuracaoEmMinutos().equals(0L)) {
			sessao.setDtFim(LocalDateTime.now().plusMinutes(1));
		}
		var sessaoSalvo = this.repository.save(sessao);
		var sessaoRetorno = this.modelMapper.map(sessaoSalvo, SessaoDTO.class);

		return sessaoRetorno;
	}

	public SessaoDTO pesquisarPorId(Long id) {
		var sessao = this.repository.findById(id);

		if (!sessao.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
			
		var sessaoDTO = this.modelMapper.map(sessao.get(), SessaoDTO.class);
		return sessaoDTO;
	}

	public List<SessaoDTO> pesquisarTodos() {
		var sessoes = this.repository.findAll();
		var sessoesDTO = sessoes.stream().map(associado -> this.modelMapper.map(associado, SessaoDTO.class))
				.collect(Collectors.toList());
		return sessoesDTO;
	}

}
