package com.desafio.pauta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.pauta.dtos.AssociadoDTO;
import com.desafio.pauta.services.AssociadoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("associado")
@Api(value = "Associado")
public class AssociadoController {

	@Autowired
	private AssociadoService service;

	@PostMapping(value = "/v1", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AssociadoDTO> criar(@RequestBody AssociadoDTO associadoDTO) {
		return new ResponseEntity<AssociadoDTO>(this.service.criar(associadoDTO), HttpStatus.CREATED);
	}

	@GetMapping(value = "/v1", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AssociadoDTO>> pesquisarTodos() {
		return new ResponseEntity<List<AssociadoDTO>>(this.service.pesquisarTodos(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/v2", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AssociadoDTO>> pesquisarTodosV2() {
		return new ResponseEntity<List<AssociadoDTO>>(this.service.pesquisarTodos(), HttpStatus.OK);
	}

	@GetMapping(value = "/v1/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiResponses(value = { @ApiResponse(code = 200, message = ""),
			@ApiResponse(code = 404, message = "Associado não encontrado.") })
	public ResponseEntity<AssociadoDTO> pesquisarPorId(@PathVariable("id") Long id) {
		return new ResponseEntity<AssociadoDTO>(this.service.pesquisarPorId(id), HttpStatus.OK);
	}

}
