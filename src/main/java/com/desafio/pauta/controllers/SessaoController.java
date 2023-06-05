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

import com.desafio.pauta.dtos.SessaoDTO;
import com.desafio.pauta.services.SessaoService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("sessao")
public class SessaoController {

	@Autowired
	private SessaoService service;

	@PostMapping(value = "/v1", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SessaoDTO> criar(@RequestBody SessaoDTO sessaoDTO) {
		return new ResponseEntity<SessaoDTO>(this.service.criar(sessaoDTO), HttpStatus.CREATED);
	}

	@GetMapping(value = "/v1", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<SessaoDTO>> pesquisarTodos() {
		return new ResponseEntity<List<SessaoDTO>>(this.service.pesquisarTodos(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/v2", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<SessaoDTO>> pesquisarTodosV2() {
		return new ResponseEntity<List<SessaoDTO>>(this.service.pesquisarTodos(), HttpStatus.OK);
	}

	@GetMapping(value = "/v1/{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ApiResponses(value = { @ApiResponse(code = 200, message = ""), @ApiResponse(code = 404, message = "Sessão não encontrada.") })
	public ResponseEntity<SessaoDTO> pesquisarPorId(@PathVariable("id") Long id) {
		return new ResponseEntity<SessaoDTO>(this.service.pesquisarPorId(id), HttpStatus.OK);
	}

}
