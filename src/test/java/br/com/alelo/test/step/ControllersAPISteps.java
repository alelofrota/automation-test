package br.com.alelo.test.step;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import io.cucumber.java.pt.Entao;
import org.junit.Assert;

import br.com.alelo.controller.dto.StudentDTO;
import br.com.alelo.controller.dto.StudentUpdateDTO;
import br.com.alelo.services.UserServiceAction;
import br.com.alelo.test.runner.SpringIntegrationTest;
import br.com.alelo.utils.ListUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class ControllersAPISteps extends SpringIntegrationTest {

    private UserServiceAction userServices = new UserServiceAction();

    private StudentDTO studentDTO;
    private StudentUpdateDTO studentUpdateDTO;

    private final String url = DEFAULT_URL + "students/";
    private String cpf = "";

    //----------------- CADASTRAR ---------------
    @Dado("que eu tenha os dados para cadastrar o estudante")
    public void queEuTenhaOsDadosParaCadastrarOEstudante(io.cucumber.datatable.DataTable dataTable) throws URISyntaxException {
      studentDTO = this.userServices.getUserFromDatatable(dataTable);
    }
    @Quando("enviar a requisicao {string} para a API")
    public void enviarARequisicaoParaAAPI(String verbo) {
        if("POST".equalsIgnoreCase(verbo)){
            this.userServices.post(studentDTO,url);
        }else if("PATCH".equalsIgnoreCase(verbo)) {
            this.userServices.patch(cpf, studentUpdateDTO, url);
        }
    }
    @Então("validar retorno {int}")
    public void validarRetorno(Integer status) {
        assertThat(this.userServices.getResponse().statusCode(), is(status));

    }

    //------------------LISTAGEM------------------------------
    @Dado("que eu envie a requisicao GET para a API")
    public void queEuEnvieARequisicaoGETParaAAPI() {
        this.userServices.get(url);

    }

    @Entao("valido estudantes com unico CPF")
    public void validoEstudantesComUnicoCPF() {
        List<Object> list = this.userServices.getResponse().getBody().jsonPath().getList("cpf");
        assertThat(list.get(0), is(not(list.get(1))));
    }
    @Entao("valido lista alfabetica")
    public void validoListaAlfabetica() {
        List<String> original = this.userServices.getResponse().getBody().jsonPath().getList("name");
        List<String> sorted = original;
        Collections.sort(sorted);
        Assert.assertEquals("Lista fora de ordem: " + original, sorted, original);
    }
    @Entao("valido livros sem serem duplicados")
    public void validoLivrosSemSeremDuplicados() {
        List<String> listBooks = this.userServices.getResponse().getBody().jsonPath().getList("books");
        assertTrue(ListUtils.encotrarDuplicidade(listBooks));
    }

    //------------------MODIFICAR-----------------------//

    @Dado("que eu tenha os dados para efetuar a alteracao do estudante")
    public void queEuTenhaOsDadosParaEfetuarAAlteracaoDoEstudante(DataTable dataTable) {
        studentUpdateDTO = this.userServices.getUserFromCpf(dataTable);
        cpf = this.userServices.cpfPatch(dataTable);
    }
    @Quando("enviar a requisicao {string} para a API da biblioteca")
    public void enviarARequisicaoParaAAPIDaBiblioteca(String verbo) {
        if ("POST".equalsIgnoreCase(verbo)) {
            this.userServices.post(studentDTO, url);
        } else if ("PATCH".equalsIgnoreCase(verbo)) {
            this.userServices.patch(cpf, studentUpdateDTO, url);
        }
    }


}
