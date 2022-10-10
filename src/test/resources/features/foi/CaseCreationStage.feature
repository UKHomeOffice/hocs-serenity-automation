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
    And I add a "Initial response" type document to the case
    And I submit a valid request acknowledgement response date
    Then the case should be moved to the "Allocation" stage
    And the read-only Case Details accordion should contain all case information entered during the "Case Creation" stage
    And the Requested Question should be displayed in the summary tab


  #HOCS-3482 HOCS-3838
  @FOIRegression
  Scenario: User is able to send an invalid request to Soft Close stage
    And I "Confirm" Case Creation check your answers
    And I select "Invalid" for the validity of the request and continue
    And I add a "Initial response" type document to the case
    And I submit an invalid request response date
    Then the case should be moved to the "Soft Close" stage
    And the read-only Case Details accordion should contain all case information entered during the "Case Creation" stage

  #HOCS-3249
  @FOIRegression
  Scenario: User is able to see the information entered when creating the FOI request
    Then the details entered when creating the case are displayed

  #HOCS-3249
  @FOIRegression
  Scenario Outline: User is able to edit the Case Details of the request at the Case Creation stage
    And I edit the "<value>" case details value at the Case Creation stage
    Then the details entered when editing the case are displayed
    Examples:
    | value                 |
#    | DATE FOI RECEIVED     |
#    | DATE RECEIVED IN KIMU |
#    | INBOUND CHANNEL       |
    | FOI TOPIC             |
#    | REQUEST QUESTION      |
#    | PRIMARY CORRESPONDENT |