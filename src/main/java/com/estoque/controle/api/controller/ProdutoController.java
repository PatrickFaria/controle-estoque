package com.estoque.controle.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.estoque.controle.api.controller.openapi.ProdutoControllerOpenApi;
import com.estoque.controle.domain.model.Produto;
import com.estoque.controle.domain.repository.ProdutoRepository;
import com.estoque.controle.domain.service.CadastroProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController implements ProdutoControllerOpenApi {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CadastroProdutoService cadastroProdutoService;

	@GetMapping
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	@GetMapping("/{produtoId}")
	public Produto buscar(@PathVariable Long produtoId) {
		return cadastroProdutoService.buscarOuFalhar(produtoId);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Produto adicionar(@RequestBody @Valid Produto produto) {
		return cadastroProdutoService.salvar(produto);
	}

	@PutMapping("/{produtoId}")
	public Produto atualizar(@PathVariable Long produtoId, @RequestBody Produto produto) {
		Produto produtoAtual = cadastroProdutoService.buscarOuFalhar(produtoId);
		BeanUtils.copyProperties(produto, produtoAtual, "id");

		return cadastroProdutoService.salvar(produtoAtual);
	}

	@DeleteMapping("/{produtoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long produtoId) {
		cadastroProdutoService.excluir(produtoId);
	}

}
