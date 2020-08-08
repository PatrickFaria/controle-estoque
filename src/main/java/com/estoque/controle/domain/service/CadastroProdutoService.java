package com.estoque.controle.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.estoque.controle.domain.exception.ProdutoNaoEncontradoException;
import com.estoque.controle.domain.model.Produto;
import com.estoque.controle.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {

	private static final String MSG_PRODUTO_NAO_ENCONTRADO = "Não existe um cadastro de produto com código %d";
	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	public void excluir(Long produtoId) {
		try {
			produtoRepository.deleteById(produtoId);
		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(
					String.format(MSG_PRODUTO_NAO_ENCONTRADO, produtoId));
		}
	}

	public Produto buscarOuFalhar(Long produtoId) {
		return produtoRepository.findById(produtoId).orElseThrow(() -> new ProdutoNaoEncontradoException(
				String.format(MSG_PRODUTO_NAO_ENCONTRADO, produtoId)));
	}

}
