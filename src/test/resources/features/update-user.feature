Feature: Update User Data

  Scenario: Update an existing user's information
    Given I am authenticated
    When I update user with id "2" setting name "Ilham Yulianto" and job "QA Engineer"
    Then the response status code should be 200
    And the response should contain the updated name and job
    And the response should contain an "updatedAt" timestamp