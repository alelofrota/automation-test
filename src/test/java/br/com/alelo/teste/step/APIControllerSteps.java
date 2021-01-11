package br.com.alelo.teste.step;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;

import br.com.alelo.controller.dto.StudentDTO;
import br.com.alelo.controller.dto.StudentUpdateDTO;
import br.com.alelo.services.UsuarioServiceActions;
import br.com.alelo.teste.runner.SpringIntegrationTest;
import br.com.alelo.utils.ListUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class APIControllerSteps extends SpringIntegrationTest {

	private UsuarioServiceActions usuarioServices = new UsuarioServiceActions();

	private StudentDTO studentDTO;
	private StudentUpdateDTO studentUpdateDTO;

	private final String url = DEFAULT_URL + "students/";

	private String cpf = "";

	// -------------- @Dado --------------//

	@Dado("que eu tenha os dados para cadastrar o estudante")
	public void queEuTenhaOsDadosParaCadastrarOEstudante(DataTable dataTable) throws URISyntaxException {
		studentDTO = this.usuarioServices.getUserFromDatatable(dataTable);
	}

	@Dado("que eu envie requisicao GET para api")
	public void queEuEnvieRequisicaoGETParaApi() {
		this.usuarioServices.get(url);
	}

	@Dado("que eu tenha os dados para alterar o estudante")
	public void queEuTenhaOsDadosParaAlterarOEstudante(DataTable dataTable) {
		studentUpdateDTO = this.usuarioServices.getUserFromCpf(dataTable);
		cpf = this.usuarioServices.cpfPatch(dataTable);
	}

	// -------------- @Quando --------------//

	@Quando("enviar requisicao {string} para api")
	public void enviarRequisicaoParaApi(String verbo) {
		if ("POST".equalsIgnoreCase(verbo)) {
			this.usuarioServices.post(studentDTO, url);
		} else if ("PATCH".equalsIgnoreCase(verbo)) {
			this.usuarioServices.patch(cpf, studentUpdateDTO, url);
		}
	}

	// -------------- @Então --------------//

	@Então("validar retorno {int}")
	public void validarRetorno(Integer status) {
		assertThat(this.usuarioServices.getResponse().statusCode(), is(status));
	}

	@Então("validar estudantes com cadastrado unico com cpf")
	public void validarEstudantesComCadastradoUnicoComCpf() {
		List<Object> list = this.usuarioServices.getResponse().getBody().jsonPath().getList("cpf");
		assertThat(list.get(0), is(not(list.get(1))));
	}

	@Então("validar lista em ordem alfabetica")
	public void validarListaEmOrdemAlfabetica() {
		List<String> original = this.usuarioServices.getResponse().getBody().jsonPath().getList("name");
		List<String> sorted = original;
		Collections.sort(sorted);
		Assert.assertEquals("Lista fora de ordem: " + original, sorted, original);
	}

	@Então("validar livros nao duplicados")
	public void validarLivrosNaoDuplicados() {
		List<String> listBooks = this.usuarioServices.getResponse().getBody().jsonPath().getList("books");
		assertTrue(ListUtils.encotrarDuplicidade(listBooks));

	}

}
