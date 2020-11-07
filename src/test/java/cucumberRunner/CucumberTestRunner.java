package cucumberRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * This class defines the test runner to be used for Cucumber.
 * Here we are using TestNG runner
 * The cucumber options we pass is for reporting. Initially the test results will be stored
 * in json format which will then be used by maven surefire plugin to generate html reports under
 * target/site/cucumber-reports folder.
 */

@CucumberOptions(tags="@Regression", features = "src/test/resources/", glue = {"steps"}, plugin = { "pretty",
		"html:target/site/cucumber-reports.html", "json:target/cucumber.json" })
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

}
