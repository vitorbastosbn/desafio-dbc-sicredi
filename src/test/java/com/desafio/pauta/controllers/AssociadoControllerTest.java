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

import com.desafio.pauta.dtos.AssociadoDTO;
import com.desafio.pauta.services.AssociadoService;

public class AssociadoControllerTest {

	@Mock
	private AssociadoService service;

	@InjectMocks
	private AssociadoController controller;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCriarAssociado() {
		// Dados de entrada de exemplo
		AssociadoDTO associadoDTO = new AssociadoDTO();

		// Resultado esperado
		AssociadoDTO expectedResult = new AssociadoDTO();
		ResponseEntity<AssociadoDTO> expectedResponse = new ResponseEntity<>(expectedResult, HttpStatus.CREATED);

		// Mock do serviço
		when(service.criar(associadoDTO)).thenReturn(expectedResult);

		// Chamada ao método do controlador
		ResponseEntity<AssociadoDTO> response = controller.criar(associadoDTO);

		// Verificações
		assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
		assertEquals(expectedResponse.getBody(), response.getBody());

		// Verifica se o método do serviço foi chamado corretamente
		verify(service, times(1)).criar(associadoDTO);
	}

	@Test
	void testPesquisarTodosAssociados() {
		// Resultado esperado
		List<AssociadoDTO> expectedResult = new ArrayList<>();
		ResponseEntity<List<AssociadoDTO>> expectedResponse = new ResponseEntity<>(expectedResult, HttpStatus.OK);

		// Mock do serviço
		when(service.pesquisarTodos()).thenReturn(expectedResult);

		// Chamada ao método do controlador
		ResponseEntity<List<AssociadoDTO>> response = controller.pesquisarTodos();

		// Verificações
		assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
		assertEquals(expectedResponse.getBody(), response.getBody());

		// Verifica se o método do serviço foi chamado corretamente
		verify(service, times(1)).pesquisarTodos();
	}

	@Test
	void testPesquisarPorIdAssociado() {
		// Dados de entrada de exemplo
		Long id = 1L;

		// Resultado esperado
		AssociadoDTO expectedResult = new AssociadoDTO();
		ResponseEntity<AssociadoDTO> expectedResponse = new ResponseEntity<>(expectedResult, HttpStatus.OK);

		// Mock do serviço
		when(service.pesquisarPorId(id)).thenReturn(expectedResult);

		// Chamada ao método do controlador
		ResponseEntity<AssociadoDTO> response = controller.pesquisarPorId(id);

		// Verificações
		assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
		assertEquals(expectedResponse.getBody(), response.getBody());

		// Verifica se o método do serviço foi chamado corretamente
		verify(service, times(1)).pesquisarPorId(id);
	}

}
