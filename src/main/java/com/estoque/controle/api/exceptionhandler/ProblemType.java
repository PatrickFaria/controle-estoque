package com.estoque.controle.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel","Mensagem incompreesível"),	
	PRODUTO_NÃO_ENCONTRADO("/produto-nao-encontrado","Produto não encontrado");	
	
	private String title;
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "http://controle.estoque.com.br" + path;
		this.title = title;
	}
	
}
