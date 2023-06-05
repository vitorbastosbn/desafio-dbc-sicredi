package com.desafio.pauta.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.desafio.pauta.dtos.VotoDTO;
import com.desafio.pauta.services.VotoService;

public class VotoControllerTest {

	@Mock
	private VotoService service;

	@InjectMocks
	private VotoController controller;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void criar() throws NotFoundException {
		// Dados de entrada de exemplo
		VotoDTO votoDTO = new VotoDTO();

		// Resultado esperado
		VotoDTO expectedResult = new VotoDTO();
		ResponseEntity<VotoDTO> expectedResponse = new ResponseEntity<>(expectedResult, HttpStatus.CREATED);

		// Mock do serviço
		when(service.criar(votoDTO)).thenReturn(expectedResult);

		// Chamada ao método do controlador
		ResponseEntity<VotoDTO> response = controller.criar(votoDTO);

		// Verificações
		assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
		assertEquals(expectedResponse.getBody(), response.getBody());

		// Verifica se o método do serviço foi chamado corretamente
		verify(service, times(1)).criar(votoDTO);
	}

}
