Feature: User Registration

  Scenario: Successfully register a new user
    Given the API base url is set
    When I send a registration request with email "eve.holt@reqres.in" and password "pistol"
    Then the response status code should be 200
    And the response should contain an "id" and "token"