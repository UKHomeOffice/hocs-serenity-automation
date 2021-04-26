@Misallocations @UKVI
Feature: Misallocations

  Background:
    Given I log in to "DECS" as user "UKVI_USER"

  @UKVIRegression1 @UKVIWorkflow
  Scenario Outline: User can select to Transfer a case from different stages
    When I create a "MPAM" case and move it to the "<stage>" stage
    And I load and claim the current case
    And I select to transfer a case to "<transferTo>" at the "<stage>" stage
    And I load and claim the current case
    Then the case should be moved to the "Awaiting Transfer" stage
    And the reason for transfer is displayed in a case note in the case timeline
    Examples:
    | transferTo  | stage     |
    | OGD         | Creation  |
    | Other       | Creation  |
    | OGD         | Triage    |
    | Other       | Triage    |
    | OGD         | Draft     |
    | Other       | Draft     |

  @UKVIRegression1 @UKVIWorkflow
  Scenario: User is able to move a case to Triage from the Awaiting Transfer stage
    And I create a single "MPAM" case and return to the dashboard
    And I load and claim the current case
    And I select to transfer a case to "OGD" at the "Creation" stage
    And I load and claim the current case
    And I select the "Transfer Rejected (Move to Triage)" action at the Awaiting Transfer stage
    And I complete the required fields for Triage and move the case to Triage
    Then the case should be moved to the "Triage" stage

  @UKVIRegression1 @UKVIWorkflow
  Scenario: User is able to close a case from the Awaiting Transfer stage
    And I create a single "MPAM" case and return to the dashboard
    And I load and claim the current case
    And I select to transfer a case to "OGD" at the "Creation" stage
    And I load and claim the current case
    And I select the "Transfer Accepted (Close Case)" action at the Awaiting Transfer stage
    Then the case should be closed

  @UKVIRegression1
  Scenario: User is able to amend the Transfer due date of a case at Awaiting Transfer
    And I create a single "MPAM" case and return to the dashboard
    And I load and claim the current case
    And I select to transfer a case to "OGD" at the "Creation" stage
    And I load and claim the current case
    And I amend the Transfer due date of the case to "01/01/2022"
    And I select the "Save Deadline for Transfer" action at the Awaiting Transfer stage
    And I navigate to the "Dashboard" page
    And I click to view the "Awaiting Transfer" workstack
    Then the Transfer deadline date is correct in the Awaiting Transfer team workstack