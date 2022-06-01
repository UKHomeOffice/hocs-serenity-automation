@FOIAllocation @FOI @FOIWorkflow
Feature: Allocation

  Background:
    Given I am logged into "CS" as user "FOI_USER"
    When I create a "FOI" case and move it to the "Allocation" stage

  #HOCS-3626 #HOCS-2326
  @FOIRegression
  Scenario: User is able to complete the Allocation stage
    When I select a Group
    And I select an Account Manager
    And I click the "Allocate Case" button
    And the Allocation text is displayed
    And I click the "Confirm Allocation" button
    Then the case should be moved to the "Acceptance" stage
    And the summary should display the owning team as the selected FOI Group
    And the read-only Case Details accordion should contain all case information entered during the "Allocation" stage