package stepdefinitions;

import org.hamcrest.Matchers;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import utils.CommonUtils;

public class BaseClass {
	
	TestBase baseUtils;
	public BaseClass(TestBase testBase) {
	this.baseUtils = testBase;
	}
	
	@BeforeAll
	public static void beforeAllScenarios() {
		System.out.println("============ Before All Scenarios =============");
		
		//Response Header validation
		ResponseSpecBuilder builder = new ResponseSpecBuilder();
		builder.expectHeader("Server", Matchers.equalTo("GitHub.com"));
		builder.expectHeader("Content-Type", Matchers.equalToIgnoringCase("application/json; charset=utf-8"));
		RestAssured.responseSpecification = builder.build();
		
		//BaseURI
		
		String env = CommonUtils.getProperty("global", "env");
		String baseUri = CommonUtils.getProperty(env, "base_URI");
		RestAssured.baseURI = baseUri;
		
	}
	
	@AfterAll
	public static void afterAllScenarios() {
		System.out.println("============ After All Scenarios =============");
	}
	
	@Before
	public void beforeTest() {
		System.out.println("=============== Before any scenarios ==============");
		baseUtils.request = RestAssured.given();
	}
	
	@Before (value = "@p1")
	public void beforeOne() {
		System.out.println("=============== Execute perticular tags ==============");
	}
	
	@After
	public void afterTest() {
		System.out.println("=============== After any scenarios ==============");
	}
	
	@After (value = "@p1")
	public void afterOne() {
		System.out.println("=============== After Execute perticular tags ==============");
	}

}
