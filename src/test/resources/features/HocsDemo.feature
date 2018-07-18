Feature: ErrorHandling

  @issues:SPG-17441,DST-2826,DST-1576 @SR11
  Scenario: User can submit HOCS Demo Form
    Given I navigate to the "HOCS Demo Form" Page
    When I submit valid detail in all fields
    Then I am taken to the "HOCS Main Form" Page

  Scenario: Get all students via API
    When I request "Workflow Service" info
    Then API returns a 200 response
