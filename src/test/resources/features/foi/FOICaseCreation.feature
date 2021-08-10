@FOICaseCreation @FOI @FOIWorkflow
Feature: FOI Case Creation

  Background:
    Given I am logged into "CS" as user "FOI_USER"
    When I create a "FOI" case and move it to the "Case Creation" stage

  Scenario: User is able to complete the FOI Case Creation Stage
    And I select how the request was received
    And I enter the correspondent details
    And I select the FOI topic
    And I enter the Request Question
    And I click the "Submit" button
    Then a case is created successfully
    And I navigate to the "Dashboard" page
    And I load the current case
    And I click the "Confirm" button
    And I select "Yes" for the validity of the request and continue
    And I click the "Continue" button
    Then the case should be moved to the "Allocation" stage