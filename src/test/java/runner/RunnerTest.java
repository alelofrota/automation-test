package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        dryRun = false,
        plugin = {"pretty", "html:target/report.html", "json:target/report.json"},
        monochrome = true,
        glue = {"steps", "base"},
        features = "src/test/resources/features",
        tags = "@StudentControllerTest"
        )


public class RunnerTest {

}
