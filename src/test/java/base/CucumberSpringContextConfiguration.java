package base;

import br.com.alelo.BookApplication;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest (classes = {BookApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberSpringContextConfiguration {

}