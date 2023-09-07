package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		features = "src/test/resources/features",
		glue="stepdefinitions",
		snippets = SnippetType.CAMELCASE,
		dryRun=false
		//tags = "@current"
		)

public class TestRunner {

}
