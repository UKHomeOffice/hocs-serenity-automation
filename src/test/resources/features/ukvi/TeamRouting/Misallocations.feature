@Misallocations @UKVI @UKVIWorkflow
Feature: Misallocations

  Background:
    Given I log in to DECS

  Scenario Outline: User can select to Transfer a case from the Data Input stage
    And I create a single "MPAM" case and return to the dashboard
    And I load and claim the current case
    And I select to transfer a case to "<transferTo>" at the "Creation" stage
    And I load and claim the current case
    Then the case should be moved to the "Awaiting Transfer" stage
    Examples:
    | transferTo  |
    | OGD         |
    | Other       |

  Scenario Outline: User can select to Transfer a case from different stages
    And I create a "MPAM" case and move it to the "<stage>" stage
    And I load and claim the current case
    And I select to transfer a case to "OGD" at the "<stage>" stage
    And I load and claim the current case
    Then the case should be moved to the "Awaiting Transfer" stage
    Examples:
      | stage     |
      | Creation  |
      | Triage    |
      | Draft     |


  Scenario: User is able to move a case to Triage from the Awaiting Transfer stage
    And I create a single "MPAM" case and return to the dashboard
    And I load and claim the current case
    And I select to transfer a case to "OGD" at the "Creation" stage
    And I load and claim the current case
    And I select the "Transfer Rejected (Move to Triage)" action at the Awaiting Transfer stage
    And I complete the required fields for Triage and move the case to Triage
    Then the case should be moved to the "Triage" stage

  Scenario: User is able to close a case from the Awaiting Transfer stage
    And I create a single "MPAM" case and return to the dashboard
    And I load and claim the current case
    And I select to transfer a case to "OGD" at the "Creation" stage
    And I load and claim the current case
    And I select the "Transfer Accepted (Close Case)" action at the Awaiting Transfer stage
    Then the case should be closed

  Scenario: User is able to amend the Transfer due date of a case at Awaiting Transfer
    And I create a single "MPAM" case and return to the dashboard
    And I load and claim the current case
    And I select to transfer a case to "OGD" at the "Creation" stage
    And I load and claim the current case
    And I amend the Transfer due date of the case to "01/01/2022"
    And I select the "Save Deadline for Transfer" action at the Awaiting Transfer stage
    And I navigate to the "home" page
    And I click to view the "Awaiting Transfer" workstack
    Then the Transfer deadline date is correct in the Awaiting Transfer team workstack

  Scenario: User is able to view the reason for transfer in the case timeline
    And I create a single "MPAM" case and return to the dashboard
    And I load and claim the current case
    And I select to transfer a case to "OGD" at the "Creation" stage
    And I load and claim the current case
    And I select the Timeline tab
    Then the reason for transfer is displayed in a case note in the case timeline