@Misallocations @UKVI @UKVIWorkflow
Feature: Misallocations

  Background:
    Given I log in to DECS
    And I create a single "MPAM" case and return to the dashboard
    And I load and claim the current case

  Scenario Outline: User can select to Transfer a case from the Data Input stage
    And I select to transfer a case to "<transferTo>"
    And I load and claim the current case
    Then the case should be moved to the "Awaiting Transfer" stage
    Examples:
    | transferTo  |
    | OGD         |
    | Other       |

  Scenario: User is able to move a case to Triage from the Awaiting Transfer stage
    And I select to transfer a case to "OGD"
    And I load and claim the current case
    And I select the "Transfer Rejected (Move to Triage)" action at the Awaiting Transfer stage
    And I complete the required fields for Triage and move the case to Triage
    Then the case should be moved to the "Triage" stage

  Scenario: User is able to close a case from the Awaiting Transfer stage
    And I select to transfer a case to "OGD"
    And I load and claim the current case
    And I select the "Transfer Accepted (Close Case)" action at the Awaiting Transfer stage
    Then the case should be closed

  Scenario: User is able to amend the Transfer due date of a case at Awaiting Transfer
    And I select to transfer a case to "OGD"
    And I load and claim the current case
    And I amend the Transfer due date of the case to "01/01/2022"
    And I select the "Save Deadline for Transfer" action at the Awaiting Transfer stage
    And I navigate to the "home" page
    And I load the current case
    Then the Transfer due date of the case should be updated