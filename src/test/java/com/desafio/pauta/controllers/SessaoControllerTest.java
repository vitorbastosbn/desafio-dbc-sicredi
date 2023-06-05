package com.desafio.pauta.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.desafio.pauta.dtos.SessaoDTO;
import com.desafio.pauta.services.SessaoService;

public class SessaoControllerTest {

	@Mock
	private SessaoService service;

	@InjectMocks
	private SessaoController controller;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void criar() {
		// Dados de entrada de exemplo
		SessaoDTO sessaoDTO = new SessaoDTO();

		// Resultado esperado
		SessaoDTO expectedResult = new SessaoDTO();
		ResponseEntity<SessaoDTO> expectedResponse = new ResponseEntity<>(expectedResult, HttpStatus.CREATED);

		// Mock do serviço
		when(service.criar(sessaoDTO)).thenReturn(expectedResult);

		// Chamada ao método do controlador
		ResponseEntity<SessaoDTO> response = controller.criar(sessaoDTO);

		// Verificações
		assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
		assertEquals(expectedResponse.getBody(), response.getBody());

		// Verifica se o método do serviço foi chamado corretamente
		verify(service, times(1)).criar(sessaoDTO);
	}

	@Test
	void pesquisarTodos() {
		// Resultado esperado
		List<SessaoDTO> expectedResult = new ArrayList<>();
		ResponseEntity<List<SessaoDTO>> expectedResponse = new ResponseEntity<>(expectedResult, HttpStatus.OK);

		// Mock do serviço
		when(service.pesquisarTodos()).thenReturn(expectedResult);

		// Chamada ao método do controlador
		ResponseEntity<List<SessaoDTO>> response = controller.pesquisarTodos();

		// Verificações
		assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
		assertEquals(expectedResponse.getBody(), response.getBody());

		// Verifica se o método do serviço foi chamado corretamente
		verify(service, times(1)).pesquisarTodos();
	}

	@Test
	void pesquisarPorId() {
		// Dados de entrada de exemplo
		Long id = 1L;

		// Resultado esperado
		SessaoDTO expectedResult = new SessaoDTO();
		ResponseEntity<SessaoDTO> expectedResponse = new ResponseEntity<>(expectedResult, HttpStatus.OK);

		// Mock do serviço
		when(service.pesquisarPorId(id)).thenReturn(expectedResult);

		// Chamada ao método do controlador
		ResponseEntity<SessaoDTO> response = controller.pesquisarPorId(id);

		// Verificações
		assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
		assertEquals(expectedResponse.getBody(), response.getBody());

		// Verifica se o método do serviço foi chamado corretamente
		verify(service, times(1)).pesquisarPorId(id);
	}

}
