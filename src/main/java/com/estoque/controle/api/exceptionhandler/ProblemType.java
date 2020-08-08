package com.estoque.controle.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	CODIGO_EXISTENTE("/codigo-existente","Código já existente."),	
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel","Mensagem incompreesível."),	
	PRODUTO_NÃO_ENCONTRADO("/produto-nao-encontrado","Produto não encontrado."),	
	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos.");
	
	private String title;
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "http://controle.estoque.com.br" + path;
		this.title = title;
	}
	
}
