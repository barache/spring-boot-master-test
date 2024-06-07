Feature: API Testing with Cucumber

  Scenario: POST Request to create a new film
    Given I set request body
    When I send the POST HTTP request
    Then I receive valid HTTP response code 201

  Scenario: GET Request to retrieve film details
    Given I set the GET film service api endpoint
    When I send the GET HTTP request
    Then I receive valid HTTP response code 200
    And I receive film details in the response

  Scenario: PUT Request to update film details
    Given I set the PUT film service api endpoint
    When I send the PUT HTTP request
    Then I receive valid HTTP response code 200
    And I receive film details updated in the response

  Scenario: DELETE Request to delete film
    Given I set the DELETE film service api endpoint
    When I send the DELETE HTTP request
    Then I receive valid HTTP response code 204
