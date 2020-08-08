package com.estoque.controle.api.controller.openapi;

import java.util.List;

import com.estoque.controle.domain.model.Produto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Produtos")
public interface ProdutoControllerOpenApi {

	@ApiOperation("Lista os Produtos")
	public List<Produto> listar();

	@ApiOperation("Busca um Produto por Id")
	public Produto buscar(@ApiParam(value = "ID de um Produto", example = "1") Long produtoId);

	@ApiOperation("Cadastra um Produto")
	public Produto adicionar(@ApiParam(name = "corpo", value = "Representação de um novo Produto") Produto produto);

	@ApiOperation("Atualiza um Produto por Id")
	public Produto atualizar(@ApiParam(value = "ID de um Produto", example = "1") Long produtoId,
			@ApiParam(name = "corpo", value = "Representação de um Produto com os novos Dados") Produto produto);

	@ApiOperation("Exclui um Produto por Id")
	public void remover(@ApiParam(value = "ID de um Produto", example = "1") Long produtoId);

}
