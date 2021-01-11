package br.com.alelo.teste.runner;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import br.com.alelo.BookApplication;
import io.cucumber.spring.CucumberContextConfiguration;

@SpringBootTest(classes = { BookApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
public abstract class SpringIntegrationTest {

	protected RestTemplate restTemplate = new RestTemplate();

	protected final String DEFAULT_URL = "http://localhost:8080/";

//	@LocalServerPort
//	private int port;
//
//	public int getPort() {
//		return port;
//	}
}
