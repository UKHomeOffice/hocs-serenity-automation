@Allocation @FOI @FOIWorkflow
Feature: Allocation

  Background:
    Given I am logged into "CS" as user "FOI_USER"
    When I create a "FOI" case and move it to the "Allocation" stage
    And I load and claim the current case

  #HOCS-3268
  Scenario: Scenario: User is able to view the question asked
    Then the Requested Question should be displayed in the summary tab

  #HOCS-3626
  Scenario: User is able to complete the Allocation stage
    And I select "HMPO" as the Directorate
    And I select "FOI HMPO Intelligence Acceptance Team" as the Acceptance Team
    And I click the "Allocate Case" button
    Then the Allocation text is displayed

  #HOCS-2326
  Scenario: User is able to complete the Allocation stage
    And I select "HMPO" as the Directorate
    And I select "FOI HMPO Intelligence Acceptance Team" as the Acceptance Team
    And I click the "Allocate Case" button
    And I click the "Confirm Allocation" button
    Then the case should be moved to the "Acceptance" stage
