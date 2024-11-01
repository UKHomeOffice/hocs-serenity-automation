@FOIConsiderAndDraft @FOI @FOIWorkflow
Feature: Consider and Draft

  Background:
    Given I am logged into "CS" as user "FOI_USER"
    When I create a "FOI" case and move it to the "Consider and Draft" stage
    And I load and claim the current case

  @FOIRegression
  Scenario: User is able to complete the Consider and Draft stage
    And I select that the case "Doesn't" require a contribution
    And I upload my Primary "Draft response" document
    And I click the "Complete Draft" button
    Then the case should be moved to the "Approval" stage
    And the selected document should be tagged as the primary draft

  #HOCS-2809, HOCS-2930
  @FOIRegression
  Scenario: User is able to request multiple contributions at the Consider and Draft stage
    And I select that the case "Does" require a contribution
    And I add 2 contribution requests to the case and move the case to the Contribution Request stage
    Then there are 2 contribution requests added to the case