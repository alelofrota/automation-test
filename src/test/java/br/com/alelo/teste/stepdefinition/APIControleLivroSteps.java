package br.com.alelo.teste.stepdefinition;

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
import br.com.alelo.suporte.Config;
import br.com.alelo.teste.runner.HooksTest;
import br.com.alelo.utils.ListUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class APIControleLivroSteps extends HooksTest {

	private Config usuarioServices = new Config();

	private StudentDTO studentDTO;
	private StudentUpdateDTO studentUpdateDTO;

	private final String url = DEFAULT_URL + "students/";

	private String cpf = "";

	//Dados

	@Dado("prencher dados para cadastrar o estudante")
	public void prencher_dados_para_cadastrar_o_estudante(DataTable dataTable) throws URISyntaxException {
		studentDTO = this.usuarioServices.getUserFromDatatable(dataTable);
	}



	@Dado("prencher dados para alterar cadastro de um estudante")
	public void prencher_dados_para_alterar_cadastro_de_um_estudante(DataTable dataTable) {
		studentUpdateDTO = this.usuarioServices.getUserFromCpf(dataTable);
		cpf = this.usuarioServices.cpfPatch(dataTable);
	}

//Requisição
	@Dado("enviar requisicao GET para api")
	public void enviar_requisicao_GET_para_api() {

		this.usuarioServices.get(url);
	}

	@Quando("enviar requisicao {string} para api")
	public void enviarRequisicaoParaApi(String verbo) {
		if ("POST".equalsIgnoreCase(verbo)) {
			this.usuarioServices.post(studentDTO, url);
		} else if ("PATCH".equalsIgnoreCase(verbo)) {
			this.usuarioServices.patch(cpf, studentUpdateDTO, url);
		}
	}


//Validação

	@Então("validar retorno {int}")
	public void validarRetorno(Integer status) {
		assertThat(this.usuarioServices.getResponse().statusCode(), is(status));
	}

	@Então("validar se os estudantes com cadastro unico com cpf")
	public void validar_se_os_estudantes_com_cadastro_unico_com_cpf() {
		List<Object> list = this.usuarioServices.getResponse().getBody().jsonPath().getList("cpf");
		assertThat(list.get(0), is(not(list.get(1))));
	}

	@Então("validar se a lista em ordem alfabetica")
	public void validar_se_a_lista_em_ordem_alfabetica() {
		List<String> original = this.usuarioServices.getResponse().getBody().jsonPath().getList("name");
		List<String> sorted = original;
		Collections.sort(sorted);
		Assert.assertEquals("Lista fora de ordem: " + original, sorted, original);
	}

	@Então("validar se os livros não são duplicados")
	public void validar_se_os_livros_nao_sao_duplicados() {
		List<String> listBooks = this.usuarioServices.getResponse().getBody().jsonPath().getList("books");
		assertTrue(ListUtils.encotrarDuplicidade(listBooks));

	}
}
