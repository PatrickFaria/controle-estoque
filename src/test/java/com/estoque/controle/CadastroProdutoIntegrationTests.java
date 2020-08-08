package com.estoque.controle;

import java.math.BigDecimal;

import javax.validation.ConstraintViolationException;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.estoque.controle.domain.model.Produto;
import com.estoque.controle.domain.service.CadastroProdutoService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroProdutoIntegrationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private CadastroProdutoService cadastroProdutoService;

	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/produtos";
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarProdutos() {
		RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveConter3Produtos_QuandoConsultarProdutos() {
		RestAssured.given().accept(ContentType.JSON).when().get().then().body("", Matchers.hasSize(3)).body("nome",
				Matchers.hasItems("Computador 1", "Computador 2"));
	}

//	@Test
//	public void deveCadastroProdutoSucesso() {
//		Produto novoProduto = new Produto();
//		novoProduto.setNome("Caixa");
//		novoProduto.setItens(2);
//		novoProduto.setValor(BigDecimal.valueOf(1000.00));
//
//		novoProduto = cadastroProdutoService.salvar(novoProduto);
//
//		Assert.assertNotNull(novoProduto);
//		Assert.assertNotNull(novoProduto.getId());
//	}

	@Test(expected = ConstraintViolationException.class)
	public void deveFalharAoCadastrarProduto_QuandoSemNome() {
		Produto novoProduto = new Produto();
		novoProduto.setNome(null);
		novoProduto.setItens(2);
		novoProduto.setValor(BigDecimal.valueOf(1000.00));

		novoProduto = cadastroProdutoService.salvar(novoProduto);
	}

}
