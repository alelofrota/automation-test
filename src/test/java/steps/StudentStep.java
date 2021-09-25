package steps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;

import org.junit.Assert;

import br.com.alelo.controller.dto.BookDTO;
import br.com.alelo.controller.dto.StudentDTO;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StudentStep {
	
	private String baseUrl = "http://localhost:8080";
	private Response response; 
	private List<String> studentList;
	
	@Dado("que eu esteja na rota de estudantes da API")
	public void que_eu_esteja_na_rota_de_estudantes_da_api() {
		RestAssured.baseURI = baseUrl;
	}
	
	@Quando("enviar a requisição para listar todos os estudantes")
	public void enviar_a_requisição_para_listar_todos_os_estudantes() {
		response = RestAssured
				.given().contentType(ContentType.JSON)
				.when().get("/students");
		
		response.getBody().prettyPrint();
		
		studentList = response.body().jsonPath().get("name");
	}
	
	@Então("a API retorna o status {int}")
	public void a_api_retorna_o_status(Integer statusCode) {
		response.then()
			.statusCode(statusCode);
	}
	
	@E("devem estar em ordem alfabética")
	public void devem_estar_em_ordem_alfabética() {
		Collections.sort(studentList);
		
		Assert.assertTrue("Os nomes não estão em ordem alfabética!", studentList.equals(response.body().jsonPath().get("name")));
				
	}
	
				
		//===========POST==============//
	
	@Quando("cadastrar um estudante com {string},{string},{string},{int}, {string},{string},{string}")
	public void cadastrar_um_estudante_com(String nome, String email, String cpf, Integer idade, String livro, String titulo, String autor) {
		
		//Montando o corpo do POST
		
		StudentDTO student = StudentDTO.builder()
				.name(nome)
				.email(email)
				.cpf(cpf)
				.years(idade)
				.books(new ArrayList<>())
				.build();
		
		BookDTO books = BookDTO.builder()
					.name(livro)
					.title(titulo)
					.author(autor)
					.build();
		
		student.getBooks().add(books);
		
		// Montando requisição POST
		response = RestAssured.given()
								.contentType(ContentType.JSON)
								.body(student)
								.log().all()
							.when()
								.post("/students");
		
		response.getBody().prettyPrint();
	}

	@Então("deve retornar os dados cadastrados {string},{string},{string},{int}, {string},{string},{string}")
	public void deve_retornar_os_dados_cadastrados(String nome, String email, String cpf, Integer idade, String livro, String titulo, String autor) {
		
		response.then()
			.assertThat()
				.body("name", is(nome))
				.body("email", is(email))
				.body("cpf", is(cpf))
				.body("years", is(idade))
				.body("books[0].name", is(livro))
				.body("books[0].title", is(titulo))
				.body("books[0].author", is(autor));
		
	}
	
	//  ========= PATCH ========= //
	

	@Quando("atualizar um estudante com {string},{string},{string}")
	public void atualizar_um_estudante_com(String nome, String email, String cpf) {
		
		StudentDTO student = StudentDTO.builder()
				.name(nome)
				.email(email)
				.build();
		
		response = RestAssured.given()
								.contentType(ContentType.JSON)
								.body(student)
								.log().all()
							.when()
								.patch("students/" + cpf);
		
		response.getBody().prettyPrint();
		
		
	}
	
	@Então("deve retornar os dados atualizados {string},{string}")
	public void deve_retornar_os_dados_atualizados(String nome, String email) {
		response.then()
		.assertThat()
			.body("name", is(nome))
			.body("email", is(email));
				
	}
	//  ========= POST - CPF Existente ========= // 
	

	@Então("a mensagem de erro {string}")
	public void a_mensagem_de_erro(String mensagem) {
		response.then()
				.assertThat()
				.body("messages[0]", is(mensagem));
		
	}

	//  ========= POST - Livro Duplicado ========= // 
	
	@Quando("cadastrar um estudante com livro duplicado com {string},{string},{string},{int}, {string},{string},{string},{string},{string},{string}")
	public void cadastrar_um_estudante_com_livro_duplicado_com(String nome, String email, String cpf, Integer idade, String livro, String titulo, String autor, String livro1, String titulo1, String autor1) {
		
		//Montando o corpo do POST
		
				StudentDTO student = StudentDTO.builder()
						.name(nome)
						.email(email)
						.cpf(cpf)
						.years(idade)
						.books(new ArrayList<>())
						.build();
				
				BookDTO books = BookDTO.builder()
							.name(livro)
							.title(titulo)
							.author(autor)
							.build();
				
				BookDTO books2 = BookDTO.builder()
						.name(livro1)
						.title(titulo1)
						.author(autor1)
						.build();
				
				student.getBooks().add(books);
				student.getBooks().add(books2);
				
				// Montando requisição POST
				response = RestAssured.given()
										.contentType(ContentType.JSON)
										.body(student)
										.log().all()
									.when()
										.post("/students");
				
				response.getBody().prettyPrint();
			}
	}

