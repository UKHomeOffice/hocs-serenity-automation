@ConsiderAndDraft @FOI @FOIWorkflow
Feature: Consider and Draft

  Background:
    Given I am logged into "CS" as user "FOI_USER"
    When I create a "FOI" case and move it to the "Consider and Draft" stage
    And I load and claim the current case

  Scenario: User is able to complete the Consider and Draft stage
    And I select that the case "Is" in the correct drafting team
    And I click the "Continue" button
    And I select that the case "Doesn't" require a contribution
    And I click the "Continue" button
    And I select "Full disclosure" as the response type
    And I click the "Continue" button
    And I click add documents
    And I choose the document type "Draft response"
    And I upload a file of type "docx"
    And I click the "Continue" button
    Then the case should be moved to the "Approval" stage

  #HOCS-2804, HOCS-2768
  Scenario: User is able to reject an FOI request at Consider and Draft
    And I select that the case "Isn't" in the correct drafting team
    And I enter a reason for the case to be returned to Acceptance
    And I click the "Continue" button
    Then the case should be moved to the "Acceptance" stage
    And a rejection note should be visible showing the reason for rejection

  Scenario: User is able to move a case with the exemption response type to Dispatch
    And I select that the case "Is" in the correct drafting team
    And I click the "Continue" button
    And I select that the case "Doesn't" require a contribution
    And I click the "Continue" button
    And I select "Exemption" as the response type
    And I click the "Continue" button
    And I select a reason for exemption
    And I click the "Continue" button
    And I click add documents
    And I choose the document type "Draft response"
    And I upload a file of type "docx"
    And I click the "Continue" button
    Then the case should be moved to the "Dispatch" stage
    
  #HOCS-2809, HOCS-2930
  Scenario: User is able to request multiple contributions at the Consider and Draft stage
    And I select that the case "Is" in the correct drafting team
    And I click the "Continue" button
    And I select that the case "Does" require a contribution
    And I click the "Continue" button
    And I add a "Case" contribution request

    