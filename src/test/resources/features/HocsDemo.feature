Feature: HOCS DEMO

  @test
  Scenario: User can submit HOCS Demo Form
    Given I am user "Dom"
    And I navigate to the "Test Form" Page
    When I submit valid detail in all fields
    Then I am taken to the "HOCS Home" Page

  Scenario: Upload document
    Given I am user "Dom"
    And I navigate to the "Create Single Case" Page
    When I create a case
    Then I am taken to the "Create Single Case" Page

  Scenario: Get Workflow Service Response
    When I request "Workflow Service" info
    Then "Workflow Service" returns a 200 response

  Scenario: Get Case Service Response
    When I request "Case Service" info
    Then "Case Service" returns a 200 response

  Scenario: Assert Case Service Response Body
    When I request "Workflow Service" info
    Then the response body has the correct contents