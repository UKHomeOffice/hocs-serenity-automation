@FOIAllocation @FOI @FOIWorkflow
Feature: Allocation

  Background:
    Given I am logged into "CS" as user "FOI_USER"
    When I create a "FOI" case and move it to the "Allocation" stage

  #HOCS-3268
  @FOIRegression
  Scenario: User is able to see the Requested Question entered at creation
    Then the Requested Question should be displayed in the summary tab

  #HOCS-3626 #HOCS-2326
  @FOIRegression
  Scenario: User is able to complete the Allocation stage
    And I select "HMPO" as the Directorate
    And I select "FOI HMPO Intelligence Acceptance Team" as the Acceptance Team
    And I select an Account Manager
    And I click the "Allocate Case" button
    And the Allocation text is displayed
    And I click the "Confirm Allocation" button
    Then the case should be moved to the "Acceptance" stage
    And the read-only Case Details accordion should contain all case information entered during the "Allocation" stage
    And an Allocation note should be visible in the timeline showing the details of the allocation