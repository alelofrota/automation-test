package br.com.alelo.test.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "./src/test/resources/features/",
        glue = "br.com.alelo.test",
        tags = "@ControllerAPI",
        plugin = { "pretty", "json:evidences/report.json" },
        monochrome = false,
        snippets = SnippetType.CAMELCASE,
        dryRun = false
)
public class RunTest {

}