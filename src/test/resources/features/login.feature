Feature: User Login

  Scenario: Successfully login with valid credentials
    Given the API base url is set
    When I send a login request with email "eve.holt@reqres.in" and password "cityslicka"
    Then the response status code should be 200
    And I should receive an authentication token