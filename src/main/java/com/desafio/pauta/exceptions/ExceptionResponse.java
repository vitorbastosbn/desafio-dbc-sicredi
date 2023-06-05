package com.desafio.pauta.exceptions;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
	
	private Date timestamp;
	private String mensagem;
	private String detalhes;

}
