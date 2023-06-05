package com.desafio.pauta.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExeptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest requisicao) {
		var excecao = new ExceptionResponse(new Date(), ex.getMessage(), requisicao.getDescription(false));
		return new ResponseEntity<>(excecao, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
