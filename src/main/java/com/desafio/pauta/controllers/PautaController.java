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

import com.desafio.pauta.dtos.PautaDTO;
import com.desafio.pauta.services.PautaService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pauta")
public class PautaController {

	@Autowired
	private PautaService service;

	@PostMapping(value = "/v1", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<PautaDTO> criar(@RequestBody PautaDTO pautaDTO) {
		return new ResponseEntity<PautaDTO>(this.service.criar(pautaDTO), HttpStatus.CREATED);
	}

	@GetMapping(value = "/v1", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<PautaDTO>> pesquisarTodos() {
		return new ResponseEntity<List<PautaDTO>>(this.service.pesquisarTodos(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/v2", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<PautaDTO>> pesquisarTodosV2() {
		return new ResponseEntity<List<PautaDTO>>(this.service.pesquisarTodos(), HttpStatus.OK);
	}

	@GetMapping(value = "/v1/{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ApiResponses(value = { @ApiResponse(code = 200, message = ""), @ApiResponse(code = 404, message = "Pauta não encontrada.") })
	public ResponseEntity<PautaDTO> pesquisarPorId(@PathVariable("id") Long id) {
		return new ResponseEntity<PautaDTO>(this.service.pesquisarPorId(id), HttpStatus.OK);
	}

}
