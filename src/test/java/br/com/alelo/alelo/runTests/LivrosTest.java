package br.com.alelo.alelo.runTests;

import br.com.alelo.alelo.AppConfiguration;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;


@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/livros.feature",
        tags = "",
        glue = { "br.com.alelo.alelo.stepsDefinitions", "br.com.alelo.alelo.config"},
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)

@ContextConfiguration(classes= AppConfiguration.class)
public class LivrosTest {
}
