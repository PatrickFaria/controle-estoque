package com.estoque.controle.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.estoque.controle.domain.exception.ProdutoNaoEncontradoException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String PRODUTO_NA0_ENCONTRADO_TYPE = "http://controle.estoque.com.br/produto-nao-encontrado";
	private static final String PRODUTO_NÃO_ENCONTRADO = "Produto não encontrado";

	@ExceptionHandler(ProdutoNaoEncontradoException.class)
	public ResponseEntity<?> handleProdutoNaoEncontradoException(ProdutoNaoEncontradoException e, WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;

		Problem problem = Problem.builder().status(status.value())
				.type(PRODUTO_NA0_ENCONTRADO_TYPE).title(PRODUTO_NÃO_ENCONTRADO)
				.detail(e.getMessage()).timestamp(LocalDateTime.now()).build();

		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null) {
			body = Problem.builder().title(status.getReasonPhrase()).status(status.value()).build();
		} else if (body instanceof String) {
			body = Problem.builder().title((String) body).status(status.value()).build();
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

}
