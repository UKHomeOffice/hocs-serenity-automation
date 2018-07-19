Feature: ErrorHandling

  @issues:SPG-17441,DST-2826,DST-1576 @SR11
  Scenario: User can submit HOCS Demo Form
    Given I login as "Dom" on "Hocs Dev"
    And I navigate to the "Test Form" Page
    When I submit valid detail in all fields
    Then I am taken to the "HOCS Home" Page

  Scenario: Get Workflow Service Response
    When I request "Workflow Service" info
    Then API returns a 200 response

  Scenario: Get Case Service Response
    When I request "Case Service" info
    Then API returns a 200 response