Feature: HOCS DEMO

  @demo
  Scenario: User can submit HOCS Demo Form
    Given I am user "Dom"
    And I navigate to the "Test Form" Page
    When I submit valid detail in all fields
    Then I am taken to the "HOCS Home" page

  @demo
  Scenario: Upload document
    Given I am user "Dom"
    And I navigate to the "Create Single Case" Page
    When I create a case
    Then I am taken to the "HOCS Home" page

  @api
  Scenario: Get Workflow Service Response
    When I request "Workflow Service" info
    Then service returns a 200 response

  @api
  Scenario: Get Case Service Response
    When I request "Case Service" info
    Then service returns a 200 response

  Scenario: Assert Case Service Response Body
    When I request "Workflow Service" info
    Then the response body has the correct contents