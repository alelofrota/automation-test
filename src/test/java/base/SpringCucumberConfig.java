package base;

import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

import br.com.alelo.BookApplication;
import io.cucumber.spring.CucumberContextConfiguration;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = BookApplication.class, loader = SpringBootContextLoader.class)
@CucumberContextConfiguration

public class SpringCucumberConfig {


}
