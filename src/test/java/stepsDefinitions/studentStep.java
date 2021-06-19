package stepsDefinitions;

import base.CucumberSpringContextConfiguration;
import br.com.alelo.controller.dto.BookDTO;
import br.com.alelo.controller.dto.StudentDTO;
import br.com.alelo.controller.dto.StudentUpdateDTO;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class studentStep extends CucumberSpringContextConfiguration  {

    private String url;
    private Response response;
    private StudentDTO[] students;

    @Dado("o endereco da API para manter o cadastro de estudantes")
    public void oEnderecoDaAPIParaManterOCadastroDeEstudantes() {
        this.url = "http://localhost:8080/students";
    }

    @Quando("realizar uma requisicao para cadastrar um novo estudante informando todos os dados")
    public void realizarUmaRequisicaoParaCadastrarUmNovoEstudanteInformandoTodosOsDados(DataTable dataTable) {

        List<List<String>> dataTableValues = dataTable.asLists();

        StudentDTO student = StudentDTO.builder()
                .name(dataTableValues.get(0).get(0))
                .email(dataTableValues.get(0).get(1))
                .years(Integer.parseInt(dataTableValues.get(0).get(2)))
                .cpf(dataTableValues.get(0).get(3))
                .books(new ArrayList<>())
                .build();

        student.getBooks().add(BookDTO.builder()
                .name(dataTableValues.get(0).get(4))
                .title(dataTableValues.get(0).get(5))
                .author(dataTableValues.get(0).get(6))
                .build());

        response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(student)
                .when()
                    .post(this.url);

    }

    @Então("a API ira retornar os dados de cadastro do estudante respondendo o codigo {int}")
    public void aAPIIraRetornarOsDadosDeCadastroDoEstudanteRespondendoOCodigo(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @Quando("realizar uma requisicao para consultar a lista de estudantes cadastrados")
    public void realizarUmaRequisicaoParaConsultarAListaDeEstudantesCadastrados() {

        response = given()
                        .when()
                        .get(this.url);

    }

    @Então("a API ira retornar todos os dados existentes respondendo o codigo {int}")
    public void aAPIIraRetornarTodosOsDadosExistentesRespondendoOCodigo(int statusCode) {
        students = response.then()
                .statusCode(statusCode)
                .extract()
                .as(StudentDTO[].class);

        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @Então("e os dados deverao ser exibidos em ordem alfabetica")
    public void eOsDadosDeveraoSerExibidosEmOrdemAlfabetica() {

        boolean isSorted = true;

        for (int i = 0; i < students.length-1; i++) {
            StudentDTO current = students[i];
            StudentDTO next = students[i+1];

            int result = current.compareTo(next);

            if (result == 1) {
                isSorted = false;
                break;
            }

        }

        Assert.assertTrue(isSorted);

    }

    @Então("nao devera ter estudantes com o CPF duplicado")
    public void naoDeveraTerEstudantesComOCPFDuplicado() {

        List<StudentDTO> studentDTOList = Arrays.asList(students);

        Boolean ok = true;

        for (StudentDTO currentStudent : students) {

            long cpfCount = studentDTOList.stream().filter(
                    student ->
                        student.getCpf().equals(currentStudent.getCpf())
            ).count();

            if (cpfCount > 1) {
                ok = false;
                break;
            }
        }

        Assert.assertTrue(ok);

    }

    @Então("não devera ter livros duplicados por estudante")
    public void nãoDeveraTerLivrosDuplicadosPorEstudante() {

        Boolean ok = true;

        for (StudentDTO student : students) {

            List<BookDTO> listOfBooks = student.getBooks();

            long count = listOfBooks.stream().distinct().count();

            if (count != listOfBooks.size()) {
                ok = false;
                break;
            }

        }

        Assert.assertTrue(ok);

    }

    @Quando("realizar uma requisicao para alterar um cadastro de estudante informando um CPF existente e os dados que devem ser alterados")
    public void realizarUmaRequisicaoParaAlterarUmCadastroDeEstudanteInformandoUmCPFExistenteEOsDadosQueDevemSerAlterados(DataTable dataTable) {

        List<List<String>> dataTableValues = dataTable.asLists();

        StudentUpdateDTO studentUpdateDTO = StudentUpdateDTO.builder()
                .email(dataTableValues.get(0).get(2))
                .name(dataTableValues.get(0).get(1))
                .build();

        response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(studentUpdateDTO)
                .when()
                        .patch(this.url+"/"+dataTableValues.get(0).get(0));

    }

}
