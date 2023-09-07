package stepdefinitions;

import io.cucumber.java.en.When;


public class CreateRepoStepDefinition {
	
	TestBase baseUtils;
	public CreateRepoStepDefinition(TestBase testBase) {
		this.baseUtils = testBase;
	}
	
	@When("I do POST request with url {string}")
	public void iDoPOSTRequestWithUrl(String url) {
		System.out.println("Executing When");  
		baseUtils.res = baseUtils.request.when()
				.log().all()
				.post(url);
	}
	

}
