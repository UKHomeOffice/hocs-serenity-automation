@CaseCreationStage @FOI @FOIWorkflow
Feature: Case Creation Stage

  Background:
    Given I am logged into "CS" as user "FOI_USER"
    When I create a "FOI" case and move it to the "Case Creation" stage
    And I load the current case

  #HOCS-3408
  Scenario: User is able to complete the Case Creation stage
    And I click the "Continue" button
    And I select "Yes" for the validity of the request and continue
    And I click the "Continue" button
    Then the case should be moved to the "Allocation" stage

  #HOCS-3482
  Scenario: User is able to send an invalid request to Dispatch
    And I click the "Continue" button
    And I select "No" for the validity of the request and continue
    And I click the "Continue" button
    Then the case should be moved to the "Dispatch" stage

  #HOCS-3249
  Scenario: User is able to see the information entered when creating the FOI request
    Then the details entered when creating the case are displayed

  #HOCS-3249
  Scenario Outline: User is able to edit the Case Details of the request at the Case Creation stage
    And I edit the "<value>" case details value at the Case Creation stage
    Then the details entered when editing the case are displayed
    Examples:
    | value                 |
    | DATE FOI RECEIVED     |
    | DATE RECEIVED IN KIMU |
    | INBOUND CHANNEL       |
    | FOI TOPIC             |
    | REQUEST QUESTION      |
    | PRIMARY CORRESPONDENT |