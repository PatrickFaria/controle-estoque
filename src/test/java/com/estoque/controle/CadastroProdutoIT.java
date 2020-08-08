package com.estoque.controle;

import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroProdutoIT {

	private static final String PATH_ID = "/produtos/1";

	private static final String BODY_EXEMPLO = "{ \"nome\": \"Computador\", \"codigo\": 123457, \"itens\": 1, \"valor\": 1000.00 }";

	@LocalServerPort
	private int port;

	@Autowired
	private Flyway flyway;

	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/produtos";

		flyway.migrate();
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarProdutos() {
		RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveRetorarStatus201_QuandoCadastrarProduto() {
		RestAssured.given().body(BODY_EXEMPLO).contentType(ContentType.JSON).accept(ContentType.JSON).when().post()
				.then().statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetorarStatus200_QuandoAtualizarProduto() {
		RestAssured.given().body(BODY_EXEMPLO).contentType(ContentType.JSON).accept(ContentType.JSON).basePath(PATH_ID)
				.when().put().then().statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveRetorarStatus204_QuandoDeletarProduto() {
		RestAssured.given().accept(ContentType.JSON).basePath(PATH_ID).when().delete().then()
				.statusCode(HttpStatus.NO_CONTENT.value());
	}

	@Test
	public void deveConter3Produtos_QuandoConsultarProdutos() {
		RestAssured.given().accept(ContentType.JSON).when().get().then().body("", Matchers.hasSize(3)).body("nome",
				Matchers.hasItems("Produto 1", "Produto 2"));
	}

}
