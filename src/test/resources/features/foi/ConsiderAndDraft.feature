@FOIConsiderAndDraft @FOI @FOIWorkflow
Feature: Consider and Draft

  Background:
    Given I am logged into "CS" as user "FOI_USER"
    When I create a "FOI" case and move it to the "Consider and Draft" stage
    And I load and claim the current case

  Scenario: User is able to complete the Consider and Draft stage
    And I select that the case "Doesn't" require a contribution
    And I click the "Continue" button
    And I click add documents
    And I choose the document type "Draft response"
    And I upload a file of type "docx"
    And I click the "Continue" button
    And I navigate to the "Dashboard" page
    Then the case should be moved to the "Approval" stage

  #HOCS-2809, HOCS-2930
  @FOIRegression
  Scenario: User is able to request multiple contributions at the Consider and Draft stage
    And I select that the case "Does" require a contribution
    And I click the "Continue" button
    And I add 2 contribution requests to the case and move the case to the Contribution Request stage
    Then there are 2 contribution requests added to the case