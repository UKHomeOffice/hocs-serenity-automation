@Misallocations @MPAM
Feature: Misallocations

  Background:
    Given I am logged into "CS" as user "MPAM_USER"

  @MPAMRegression1 @MPAMWorkflow
  Scenario Outline: User can select to Transfer a case from different stages
    When I create a "MPAM" case and move it to the "<stage>" stage
    And I load and claim the current case
    And I select to transfer a case to "<transferTo>" at the "<stage>" stage
    And I load the current case
    Then the case should be moved to the "Awaiting Transfer" stage
    And a Case transfer reason note is visible in the timeline showing the submitted reason for the transfer request
    Examples:
    | transferTo  | stage     |
    | OGD         | Creation  |
    | Other       | Creation  |
    | OGD         | Triage    |
    | Other       | Triage    |
    | OGD         | Draft     |
    | Other       | Draft     |

  @MPAMRegression1 @MPAMWorkflow
  Scenario: User is able to move a case to Triage from the Awaiting Transfer stage
    And I create a single "MPAM" case and return to the dashboard
    And I load and claim the current case
    And I select to transfer a case to "OGD" at the "Creation" stage
    And I load the current case
    And I select the "Transfer Rejected (Move to Triage)" action at the Awaiting Transfer stage
    And I complete the required fields for Triage and move the case to Triage
    Then the case should be moved to the "Triage" stage

  @MPAMRegression1 @MPAMWorkflow
  Scenario: User is able to close a case from the Awaiting Transfer stage
    And I create a single "MPAM" case and return to the dashboard
    And I load and claim the current case
    And I select to transfer a case to "OGD" at the "Creation" stage
    And I load the current case
    And I select the "Transfer Accepted (Close Case)" action at the Awaiting Transfer stage
    Then the case should be closed

  @MPAMRegression1
  Scenario: User is able to amend the Transfer due date of a case at Awaiting Transfer
    And I create a single "MPAM" case and return to the dashboard
    And I load and claim the current case
    And I select to transfer a case to "OGD" at the "Creation" stage
    And I load the current case
    And I amend the Transfer due date of the case
    And I select the "Save Deadline for Transfer" action at the Awaiting Transfer stage
    And I view the "Awaiting Transfer" workstack
    Then the I can see the new transfer deadline displayed as the cases deadline