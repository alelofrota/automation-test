package br.com.alelo.alelo.actions;

import br.com.alelo.alelo.config.CucumberSpringConfiguration;
import br.com.alelo.alelo.controller.dto.BookDTO;
import br.com.alelo.alelo.controller.dto.StudentDTO;
import br.com.alelo.alelo.controller.dto.StudentUpdateDTO;
import br.com.alelo.alelo.domain.Student;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.github.thiagonego.alfred.cpf.CPF;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static io.restassured.RestAssured.*;

@Component
public class ActionsEstudantes {

    StudentDTO student = new StudentDTO();
    List<BookDTO> listBooks = new ArrayList<BookDTO>();
    BookDTO book;
    Faker faker = new Faker();
    RequestSpecification httpRequest = RestAssured.given();
    Response response;
    StudentUpdateDTO studentUpdate = new StudentUpdateDTO();


    private static final String PATH_STUDENTS = "/students";


    public StudentDTO gerarEstudanteLivros() {
       student = StudentDTO.builder()
                .name(faker.name().firstName())
                .cpf(CPF.gerar())
                .email(student.getName() + "@testdesafioalelo.com.br")
                .years(20)
                .books(gerarLivros())
                .build();
        return student;
    };

    public List<BookDTO> gerarLivros() {
        book = BookDTO.builder()
                .name(faker.book().title())
                .title(faker.book().title())
                .author(faker.book().author())
                .build();
        listBooks.add(book);
        return listBooks;
    }

    public Response servicoCadastroEstudante(StudentDTO studantRequest) {
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(studantRequest);
        response = httpRequest.post(baseURI + ":" + DEFAULT_PORT + PATH_STUDENTS);
        return response;
    }

    public Response consultarEstudantesCadastrados() {
        httpRequest.header("Content-Type", "application/json");
        response = httpRequest.get(baseURI + ":" + DEFAULT_PORT + PATH_STUDENTS);
        return response;
    }

    public Response alterarCadastroEstudante(StudentUpdateDTO studantUpdateRequest, String cpf) {
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(studantUpdateRequest);
        response = httpRequest.patch(baseURI + ":" + DEFAULT_PORT + PATH_STUDENTS + "/" + cpf);
        return response;
    }

    public StudentUpdateDTO dadosAlteracaoEstudante(){
        studentUpdate = StudentUpdateDTO.builder()
                .name(faker.name().firstName())
                .email(studentUpdate.getName() + "@testdesafioalelo.com.br")
                .build();
        return studentUpdate;
    }
}
