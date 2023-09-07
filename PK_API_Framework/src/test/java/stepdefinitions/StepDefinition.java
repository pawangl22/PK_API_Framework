package stepdefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyData;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import pojo.GitHubCreateRepoPOJO;
import pojo.GitHubCreateRepoResponsePOJO;
import utils.CommonUtils;

public class StepDefinition {

	TestBase baseUtils;

	public StepDefinition(TestBase testBase) {
		this.baseUtils = testBase;
	}

	@Given("path param {string} with value {string}")
	public void pathParamWithValue(String paramName, String paramValue) {
		System.out.println("Executing Given");
		baseUtils.request = baseUtils.request.pathParam(paramName, paramValue);
	}

	@Given("query param {string} with value {string}")
	public void queryParamWithValue(String queryName, String queryValue) {
		baseUtils.request.given().queryParam(queryName, queryValue);
	}

	@Given("header {string} with value {string}")
	public void headerWithValue(String key, String value) {
		baseUtils.request.given().header(key, value);
	}

	@When("I do GET request with url {string}")
	public void iDoGetRequestWithUrl(String url) {
		System.out.println("Executing When");
		baseUtils.res = baseUtils.request.when().log().all().get(url);
	}

	@Given("body from file {string}")
	public void bodyFromFile(String fileName) {
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/inputjson/" + fileName);
			baseUtils.request.body(fis);
			baseUtils.request.contentType(ContentType.JSON);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Given("body {string} with value {string}")
	public void bodyWithValue(String paramName, String paramValue) {
		GitHubCreateRepoPOJO obj = CommonUtils.getCreateRepoPOJOObj(paramName, paramValue);
		baseUtils.request.contentType(ContentType.JSON);
		baseUtils.request.body(obj);
	}

	@Then("validate {string} with value {string}")
	public void validateWithValue(String paramName, String paramValue) {
		CommonUtils.validateCreateResponsePOJO(baseUtils.res, paramName, paramValue);
	}

	@Then("validate status code is {int}")
	public void validateStatusCodeIs(Integer statusCode) {
		System.out.println("Executing Then");
		// String resBody = res.then().extract().body().asString();
		// ExtentCucumberAdapter.addTestStepLog(resBody);
		baseUtils.res.then().log().body().statusCode(statusCode);
	}
}
