@FOICaseCreationStage @FOI @FOIWorkflow
Feature: Case Creation Stage

  Background:
    Given I am logged into "CS" as user "FOI_USER"
    When I create a "FOI" case and move it to the "Case Creation" stage
    And I load the current case

  #HOCS-3408
  Scenario: User is able to complete the Case Creation stage
    And I "Confirm" Case Creation check your answers
    And I select "Valid" for the validity of the request and continue
    And I upload a "Initial response" document
    And I fill the acknowledgement response date
    And I click the "Complete Create" button
    Then the FOI case should be moved to the "Allocation" stage
#    And the read-only Case Details accordion should contain all case information entered during the "Case Creation" stage

  #HOCS-3482
  @FOIRegression
  Scenario: User is able to send an invalid request to Dispatch
    And I "Confirm" Case Creation check your answers
    And I select "No" for the validity of the request and continue
    And I click the "Complete Create" button
    And I navigate to the "Dashboard" page
    Then the case should be moved to the "Dispatch" stage

  #HOCS-3249
  @FOIRegression
  Scenario: User is able to see the information entered when creating the FOI request
    Then the details entered when creating the case are displayed

  @FOIRegression
  Scenario: User is able to extend the deadline of an FOI case
    And I select the actions tab
    And I select to extend the deadline of the FOI case
    And I navigate to the "Dashboard" page
    And I load the current case
    And I select the summary tab
    Then the deadline of the FOI case should be extended

  @FOIRegression
  Scenario: User is able to add and complete an Appeal for an FOI case
    And I select the actions tab
    And I select the manage appeals hypertext
    And I add an appeal to the FOI case
    And I enter the appeal information and complete the appeal
    And I select the summary tab
    Then the information entered for the FOI appeal should be displayed

  #HOCS-3249
  @FOIRegression
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