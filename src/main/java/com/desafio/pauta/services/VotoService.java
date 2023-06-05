package com.desafio.pauta.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.desafio.pauta.dtos.StatusCpfDTO;
import com.desafio.pauta.dtos.VotoDTO;
import com.desafio.pauta.entities.Associado;
import com.desafio.pauta.entities.Sessao;
import com.desafio.pauta.entities.Voto;
import com.desafio.pauta.entities.VotoId;
import com.desafio.pauta.repositories.AssociadoRepository;
import com.desafio.pauta.repositories.SessaoRepository;
import com.desafio.pauta.repositories.VotoRepository;

@Service
public class VotoService {
	
	private final String DESABILITADO_PARA_VOTAR = "UNABLE_TO_VOTE";

	@Autowired
	private VotoRepository repositoy;

	@Autowired
	private SessaoRepository sessaoRepository;
	
	@Autowired
	private AssociadoRepository associadoRepository;

	public VotoDTO criar(VotoDTO votoDTO) throws NotFoundException {
		var sessao = pesquisarPautaSessaoAtual(votoDTO);
		var associado = this.associadoRepository.findById(votoDTO.getIdAssociado()).orElseThrow(() -> new NotFoundException());

		if (verificarExisteVotoSessaoAtual(votoDTO)
				|| verificarExisteVotoMesmaPauta(sessao.getPauta().getId(), votoDTO.getIdAssociado())
				|| verificarCPFDesabilitadoParaVotacao(associado)) {
			throw new RuntimeException("Voto não pode ser computado.");
		}

		var votoParaSalvar = this.converterDTOParaEntidade(votoDTO);

		if (LocalDateTime.now().isAfter(sessao.getDtFim())) {
			throw new RuntimeException("Voto fora do período da sessão.");
		}
		
		this.repositoy.save(votoParaSalvar);

		return votoDTO;
	}

	private boolean verificarCPFDesabilitadoParaVotacao(Associado associado) {
		var restTemplate = new RestTemplate();
		
		var response = new ResponseEntity<StatusCpfDTO>(HttpStatus.OK);
		
		try {
			response = restTemplate.getForEntity(String.format("https://user-info.herokuapp.com/users/%s", associado.getCpf()), StatusCpfDTO.class);
			var statusCPF = response.getBody();
			return statusCPF.getStatus().equals(DESABILITADO_PARA_VOTAR);
		} catch (Exception e) {
			// API Disponibilizada não existe no momento da implantação deste desafio. Sendo ignorada para seguir o fluxo.
			return false;
		}
	}

	private boolean verificarExisteVotoMesmaPauta(Long idPauta, Long idAssociado) {
		var sessoesComMesmaPauta = this.sessaoRepository.pesquisarSessaoPorPauta(idPauta);
		
		if (sessoesComMesmaPauta.size() <= 1) {
			return false;
		}
		
		return sessoesComMesmaPauta.stream()
	            .anyMatch(sessao -> sessao.getAssociados().stream()
	                    .anyMatch(associado -> associado.getId().equals(idAssociado)));
	}

	private boolean verificarExisteVotoSessaoAtual(VotoDTO votoDTO) {
		var votoNaSessaoAtual = this.repositoy.findById(new VotoId(votoDTO.getIdSessao(), votoDTO.getIdAssociado()));
		return votoNaSessaoAtual.isPresent();
	}

	private Sessao pesquisarPautaSessaoAtual(VotoDTO votoDTO) {
		var sessao = this.sessaoRepository.findById(votoDTO.getIdSessao());
		return sessao.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrada."));
	}

	private Voto converterDTOParaEntidade(VotoDTO votoDTO) {
		var voto = new Voto();
		var votoId = new VotoId(votoDTO.getIdSessao(), votoDTO.getIdAssociado());

		voto.setId(votoId);
		voto.setVoto(votoDTO.getVoto());

		return voto;
	}

}
