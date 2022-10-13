@CopyToNumber10 @DCU
Feature: Copy To Number 10

  Background:
    Given I am logged into "CS" as user "DCU_USER"

#    Expected intermittent failure. Defect HOCS-4287 raised.
  @DCUWorkflow @DCURegression
  Scenario Outline: User completes a case that needed to be copied to Number 10
    When I get a "<caseType>" case at the "Copy To Number 10" stage
    And I click the "Finish" button
    Then the case should be closed
    Examples:
      | caseType  |
      | MIN       |
#      | TRO       |