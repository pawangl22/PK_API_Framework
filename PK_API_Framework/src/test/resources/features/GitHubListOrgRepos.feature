Feature: List organization repositories
  This feature file is for automating the GET list of organization repos endpoint

		@smoke
		@p1
 		Scenario: Valid org in path param
    Given path param "org" with value "orgpawanapivalidation"
		When I do GET request with url "orgs/{org}/repos"
		Then validate status code is 200

		@p1
		Scenario: Invalid org in path param
    Given path param "org" with value "pkgl"
		When I do GET request with url "orgs/{org}/repos"
		Then validate status code is 404
		
		@smoke
		Scenario: Valid org in path param with per_page query param
    Given path param "org" with value "orgpawanapivalidation"
    And query param "per_page" with value "1"
		When I do GET request with url "orgs/{org}/repos"
		Then validate status code is 200
		
		@smoke
		Scenario: Valid org in path param with type query param and token
    Given path param "org" with value "orgpawanapivalidation"
    And query param "type" with value "private"
    And header "Authorization" with value "Bearer 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"
		When I do GET request with url "orgs/{org}/repos"
		Then validate status code is 200
		
		@p1
		Scenario: Valid org in path param with type query param and token
    Given path param "org" with value "orgpawanapivalidation"
    And query param "type" with value "private"
    And header "Authorization" with value "Bearer g1234"
		When I do GET request with url "orgs/{org}/repos"
		Then validate status code is 401
		
		@p1
		Scenario Outline: List org repo scenarios
		Given path param "org" with value "<orgName>"
    And query param "type" with value "<type>"
    And header "Authorization" with value "<token>"
		When I do GET request with url "orgs/{org}/repos"
		Then validate status code is <statusCode>
		
		Examples:
		|orgName							|	type		|	token	| statusCode|
		|orgpawanapivalidation|	public	| 			|	200				|
		|pkgl									|	public	| 			|	404				|
		|orgpawanapivalidation|	private	| Bearer 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		|	200		|
		
		
		