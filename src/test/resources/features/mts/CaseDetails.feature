@CaseDetails @MTS
Feature: Case Details

  Background:
    Given I log in to DECS
    And I create a single "MTS" case and return to the dashboard
    And I load and claim the current case

  Scenario: The user is able to complete and close an MTS Case
    And I complete the Case Details stage and close the MTS Case
    Then the case should be closed

  Scenario: The user is able to put an MTS Case On Hold
    And I put the MTS case on hold
    Then the case should be moved to the "On Hold" stage

  @Validation
  Scenario Outline: User triggers error messages at the MTS Case Details stage
    And I trigger the "<errorMessage>" error at the MTS Case Details stage
    Then the "<errorMessage>" error message should be displayed at MTS Case Details Stage
    Examples:
    | errorMessage          |
    | Primary Correspondent |
    | Business Area         |
    | Business Unit         |
    | Urgency               |
    | Channel Received      |
    | Enquiry Subject       |
    | Enquiry Reason        |
    | Actions               |