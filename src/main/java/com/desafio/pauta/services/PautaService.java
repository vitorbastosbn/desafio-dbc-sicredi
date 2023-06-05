package com.desafio.pauta.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.desafio.pauta.dtos.PautaDTO;
import com.desafio.pauta.entities.Pauta;
import com.desafio.pauta.entities.Sessao;
import com.desafio.pauta.enums.SimNaoEnum;
import com.desafio.pauta.enums.SituacaoEnum;
import com.desafio.pauta.repositories.PautaRepository;
import com.desafio.pauta.repositories.VotoRepository;

@Service
public class PautaService {

	@Autowired
	private PautaRepository repository;

	@Autowired
	private VotoRepository votoRepository;

	@Autowired
	private ModelMapper modelMapper;

	public PautaDTO criar(PautaDTO dto) {
		var pauta = this.modelMapper.map(dto, Pauta.class);
		var pautaSalva = this.repository.save(pauta);
		var pautaRetorno = this.modelMapper.map(pautaSalva, PautaDTO.class);

		return pautaRetorno;
	}

	public PautaDTO pesquisarPorId(Long id) {
		return this.repository.findById(id).map(pauta -> {
			var pautaDTO = this.modelMapper.map(pauta, PautaDTO.class);

			if (pauta.getSessoes().isEmpty() || verificarSessaoAberta(pauta))
				return pautaDTO;

			var idsSessoes = pauta.getSessoes().stream().map(Sessao::getId).collect(Collectors.toList());
			verificarResultadoVotacao(pautaDTO, idsSessoes);

			return pautaDTO;

		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	private void verificarResultadoVotacao(PautaDTO pautaDTO, List<Long> idsSessoes) {
		var votosNao = this.votoRepository.contagemVotosPorPauta(idsSessoes, SimNaoEnum.NAO.getValor());
		var votosSim = this.votoRepository.contagemVotosPorPauta(idsSessoes, SimNaoEnum.SIM.getValor());
		
		switch (votosSim.compareTo(votosNao)) {
			case 0: {
				pautaDTO.setResultado(SituacaoEnum.EMPATE);
				break;
			}
			case 1: {
				pautaDTO.setResultado(SituacaoEnum.APROVADO);
				break;
			}
			case -1: {
				pautaDTO.setResultado(SituacaoEnum.REPROVADO);
				break;
			}
		}
	}

	private boolean verificarSessaoAberta(Pauta pauta) {
		var agora = LocalDateTime.now();
		return pauta.getSessoes().stream().anyMatch(sessao -> agora.isBefore(sessao.getDtFim()));
	}

	public List<PautaDTO> pesquisarTodos() {
		var pautasDTO = new ArrayList<PautaDTO>();
		
		this.repository.findAll().forEach(pauta -> {
			var pautaDTO = this.modelMapper.map(pauta, PautaDTO.class);
			
			if (pauta.getSessoes().isEmpty() || verificarSessaoAberta(pauta)) return;
			
			var idsSessoes = pauta.getSessoes().stream().map(Sessao::getId).collect(Collectors.toList());
			verificarResultadoVotacao(pautaDTO, idsSessoes);
			
			pautasDTO.add(pautaDTO);
		});
		
		return pautasDTO;
	}

}
