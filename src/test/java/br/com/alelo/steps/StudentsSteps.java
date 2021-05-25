package br.com.alelo.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class StudentsSteps {

    private BD dados;
    private Response response;

    // ----------------- API -----------------

    public void validadorDeBody(int statusCode, String mensagem) {
        assertEquals(statusCode, response.statusCode());
        assertThat(mensagem, containsString(mensagem));
    }

    public void postRequest(String body, String path) {

        response = given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(path)
                .then()
                .extract().response();

    }

    public void pathRequest(String body, String path) {

        response = given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .patch(path)
                .then()
                .extract().response();

    }

    public void getRequest(String path) {

        response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .extract().response();
    }

    // ----------------------------------------- steps abaixo ...

    @Given("^serviço de estudantes esteja operacional$")
    public void serviçoDeEstudantesEstejaOperacional() {

        RestAssured.baseURI = "http://localhost:8080";
        dados = new BD();

    }

    @When("^devo criar um estudante via post passando nome e cpf \"([^\"]*)\" , \"([^\"]*)\"$")
    public void devoCriarUmEstudanteViaPostPassandoNomeECpf(String nome, String cpf) {

        postRequest(dados.setNomeCPF(nome, cpf).getStudent(), "/students");

    }

    @Then("^validar qualquer campo no body da consulta e status code (\\d+)$")
    public void validarQualquerCampoNoBodyDaConsultaEStatusCode(int statusCode) {

        assertEquals(statusCode, response.statusCode());
        assertEquals("Paulo Coelho", response.jsonPath().getString("name"));

    }

    @When("^devo criar um estudante via post passando nome e cpf e email vazio \"([^\"]*)\" , \"([^\"]*)\", \"([^\"]*)\"$")
    public void devoCriarUmEstudanteViaPostPassandoNomeECpfEEmailVazio(String nome, String cpf, String email) {
        postRequest(dados.setNomeCPFEmail(nome, cpf, email).getStudent(), "/students");
    }

    @When("^devo consultar um estudante via get passando cpf , \"([^\"]*)\"$")
    public void devoConsultarUmEstudanteViaGetPassandoCpf(String cpf) throws Throwable {
        getRequest("/students");
    }

    @When("^devo efetuar uma consulta no serviço que lista todos os estudantes$")
    public void devoEfetuarUmaConsultaNoServiçoQueListaTodosOsEstudantes() {
        getRequest("/students");
    }

    @Then("^validar se na resposta contem estudantes duplicados$")
    public void validarSeNaRespostaContemEstudantesDuplicados() {

        ArrayList arrayList = new ArrayList<HashMap<String , Object>>();
        arrayList = response.jsonPath().get();

        Assertions.assertThat(arrayList).doesNotHaveDuplicates();
    }

    @When("^devo atualizar um estudante via path passando nome cpf e novo email , \"([^\"]*)\", \"([^\"]*)\" , \"([^\"]*)\"$")
    public void devoAtualizarUmEstudanteViaPathPassandoNomeCpfENovoEmail(String nome, String cpf, String novoEmail) {
        pathRequest(dados.setNomeCPFEmail(nome, cpf, novoEmail).getStudent(), "/students/" + cpf);
    }

    @Then("^validar o novo nome atualizado e status code (\\d+) , \"([^\"]*)\"$")
    public void validarONovoNomeAtualizadoEStatusCode(int statusCode, String nomeAtualizado) {
        validadorDeBody(statusCode, nomeAtualizado);
    }

    @Then("^validar a mensagem de erro e status code (\\d+), \"([^\"]*)\"$")
    public void validarAMensagemDeErroEStatusCode(int statusCode, String mensagem) throws Throwable {
        validadorDeBody(statusCode, mensagem);
    }

}
