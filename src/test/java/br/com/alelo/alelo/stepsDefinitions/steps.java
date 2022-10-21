package br.com.alelo.alelo.stepsDefinitions;

import br.com.alelo.alelo.actions.ActionsEstudantes;
import br.com.alelo.alelo.controller.dto.StudentDTO;
import br.com.alelo.alelo.controller.dto.StudentUpdateDTO;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class steps {

    @Autowired
    ActionsEstudantes estudantesActions;

    StudentDTO studantRequest;

    StudentUpdateDTO studantUpdateRequest;

    Response response;


    @Dado("que tenha um estudante com livros")
    public void que_tenha_um_estudante_com_livros() {
         studantRequest = estudantesActions.gerarEstudanteLivros();
    }
    @Quando("chamar o serviço de cadastro")
    public void chamar_o_serviço_de_cadastro() {
         response = estudantesActions.servicoCadastroEstudante(studantRequest);
    }
    @Entao("devo receber o codigo de retorno {int}")
    public void devo_receber_o_codigo_de_retorno(int codStatus) {
        Assert.assertEquals(codStatus, response.getStatusCode());
    }


    @Dado("que tenha um estudante ja cadastrado")
    public void que_tenha_um_estudante_ja_cadastrado() {
        studantRequest = estudantesActions.gerarEstudanteLivros();
        this.chamar_o_serviço_de_cadastro();

    }
    @Entao("a mensagem {string}")
    public void a_mensagem(String mensagem) {
        Assert.assertTrue(response.getBody().asString().contains(mensagem));
    }

    @Quando("chamar o serviço de consulta")
    public void chamarOServiçoDeConsulta() {
       response = estudantesActions.consultarEstudantesCadastrados();
    }

    @E("a lista de todos os estudantes cadastrados")
    public void aListaDeTodosOsEstudantesCadastrados() {
        Assert.assertFalse(response.asString().isEmpty());
    }

    @E("tenha os dados para alteracao")
    public void tenhaOsDadosParaAlteracao() {
        studantUpdateRequest = estudantesActions.dadosAlteracaoEstudante();
    }

    @Quando("chamar o serviço de alterar")
    public void chamarOServiçoDeAlterar() {
       response =  estudantesActions.alterarCadastroEstudante(studantUpdateRequest, studantRequest.getCpf());
    }


    @Dado("que tenha um estudante nao cadastrado")
    public void queTenhaUmEstudanteNaoCadastrado() {
        studantRequest = estudantesActions.gerarEstudanteLivros();
    }
}
