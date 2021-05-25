package br.com.alelo.suporte;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import br.com.alelo.controller.dto.BookDTO;
import br.com.alelo.controller.dto.StudentDTO;
import br.com.alelo.controller.dto.StudentUpdateDTO;
import br.com.alelo.utils.ParserUtils;
import io.cucumber.datatable.DataTable;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Config implements ApiService {

	private Response response;

	private static String cpf = "";

	private RequestSpecBuilder getRequestHeader() {
		RequestSpecBuilder request = new RequestSpecBuilder();
		request.setAccept("*/*");
		request.setContentType("application/json");
		return request;
	}

	private void getResponseFromPost(RequestSpecBuilder request, String url) {
		RequestSpecification resquestSpec = request.build();
		response = given().log().all().spec(resquestSpec).post(url);
		response.prettyPrint();
	}

	@Override
	public void get(String url) {
		RequestSpecBuilder request = getRequestHeader();

		RequestSpecification resquestSpec = request.build();
		response = given().log().all().spec(resquestSpec).get(url);
		response.prettyPrint();

	}

	@Override
	public void post(Object obj, String url) {
		RequestSpecBuilder request = getRequestHeader();
		request.setBody(ParserUtils.objectToJson(obj));

		getResponseFromPost(request, url);
		setCpf(response.body().jsonPath().getString("cpf"));
	}

	@Override
	public void patch(String cpf, Object obj, String url) {

		RequestSpecBuilder request = getRequestHeader();
		request.setBody(obj);
		RequestSpecification resquestSpec = request.build();
		String urlPatch = url + cpf;
		patchResponse(resquestSpec, urlPatch);

	}

	private void patchResponse(RequestSpecification resquestSpec, String format) {
		response = given().log().all().spec(resquestSpec).patch(format);
		response.prettyPrint();
	}

	public StudentDTO getUserFromDatatable(DataTable dataTable) {

		List<BookDTO> bookList = new ArrayList<BookDTO>();

		BookDTO bookDTO = BookDTO.builder().author(ParserUtils.mapperDataTable(dataTable, 4))
				.name(ParserUtils.mapperDataTable(dataTable, 5)).title(ParserUtils.mapperDataTable(dataTable, 6))
				.build();

		bookList.add(bookDTO);

		return StudentDTO.builder().name(ParserUtils.mapperDataTable(dataTable, 0))
				.email(ParserUtils.mapperDataTable(dataTable, 1))
				.years(Integer.parseInt(ParserUtils.mapperDataTable(dataTable, 2)))
				.cpf(ParserUtils.mapperDataTable(dataTable, 3)).books(bookList).build();
	}

	public StudentUpdateDTO getUserFromCpf(DataTable dataTable) {

		return StudentUpdateDTO.builder().name(ParserUtils.mapperDataTable(dataTable, 1))
				.email(ParserUtils.mapperDataTable(dataTable, 2)).build();
	}

	public String cpfPatch(DataTable dataTable) {
		return ParserUtils.mapperDataTable(dataTable, 0);
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public static String getCpf() {
		return cpf;
	}

	public static void setCpf(String cpf) {
		Config.cpf = cpf;
	}
}
