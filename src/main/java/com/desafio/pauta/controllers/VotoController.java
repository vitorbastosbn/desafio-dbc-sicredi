package com.desafio.pauta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.pauta.dtos.VotoDTO;
import com.desafio.pauta.services.VotoService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("voto")
public class VotoController {

	@Autowired
	private VotoService service;

	@PostMapping(value = "/v1", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Associado não encontrado."), @ApiResponse(code = 500, message = "") })
	public ResponseEntity<VotoDTO> criar(@RequestBody VotoDTO votoDTO) throws NotFoundException {
		return new ResponseEntity<VotoDTO>(this.service.criar(votoDTO), HttpStatus.CREATED);
	}
}
