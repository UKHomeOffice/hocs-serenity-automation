@CopyToNumber10 @DCU
Feature: Copy To Number 10

  Background:
    Given I am logged into "CS" as user "DCU_USER"

  @DCUWorkflow @DCURegression
  Scenario: User completes a case that needed to be copied to Number 10
    When I get a "MIN" case at the "Copy To Number 10" stage
    And I click the "Finish" button
    Then the case should be closed