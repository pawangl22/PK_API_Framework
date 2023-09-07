Feature: Create repositories
  This feature file is for automating the Create repos

  Scenario: Valid org [path param] valid name, valid token
    Given path param "org" with value "orgpawanapivalidation"
    And header "Authorization" with value "Bearer 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"
    And body from file "githubcreaterepo.json"
    When I do POST request with url "https://api.github.com/orgs/{org}/repos"
    Then validate status code is 201

  @current
  Scenario Outline: Validating negative scenarios
    Given path param "org" with value "<orgName>"
    And header "Authorization" with value "<token>"
    And body "name,description,private" with value "<repoName>,<description>,<private>"
    When I do POST request with url "https://api.github.com/orgs/{org}/repos"
    Then validate status code is <statusCode>
    And validate "name,description" with value "<repoName>,<description>"

    Examples: 
      | orgName               | repoName          | token                                           | statusCode | description            | private |
      | pkgl                  | repo_api_May16_00 | Bearer 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 |        404 | Testing is in progress | false   |
      | orgpawanapivalidation | repo_api_May17_10 | Bearer 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 |        201 | Testing is in progress | true    |
