@MultipleContributions @MPAM
Feature: Multiple Contributions

  Background:
    Given I am logged into "CS" as user "MPAM_USER"

  @MPAMRegression1 @MPAMWorkflow
  Scenario: User can send a Triage case to the Triage (Contribution Requested) stage
    And I create a "MPAM" case and move it to the "Triage" stage
    And I load and claim the current case
    And I send the Triage case to "Contributions Requested"
    Then the case should be moved to the "Triage (Contribution Requested)" stage

  @MPAMRegression1 @MPAMWorkflow
  Scenario: User can send a Draft case to the Draft (Contribution Requested) stage
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "Contributions Requested"
    Then the case should be moved to the "Draft (Contribution Requested)" stage

  Scenario Outline: User can mark a contribution as complete or cancelled at the Triage (Contributions Requested) stage
    And I create a "MPAM" case and move it to the "Triage" stage
    And I load and claim the current case
    And I send the Triage case to "Contributions Requested"
    And I load and claim the current case
    And I choose to "<action>" the contribution request at the multiple contribution stage
    Then the "case" contribution request should be marked as "<action>"
    Examples:
      | action   |
      | Complete |
      | Cancel   |

  Scenario Outline: User can mark a contribution as complete or cancelled at the Draft (Contributions Requested) stage
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "Contributions Requested"
    And I load and claim the current case
    And I choose to "<action>" the contribution request at the multiple contribution stage
    Then the "case" contribution request should be marked as "<action>"
    Examples:
      | action   |
      | Complete |
      | Cancel   |

  @MPAMRegression1 @MPAMWorkflow
  Scenario: User can complete the Triage (Contributions Requested) stage and move the case back to Triage
    And I create a "MPAM" case and move it to the "Triage" stage
    And I load and claim the current case
    And I send the Triage case to "Contributions Requested"
    And I load and claim the current case
    And I choose to "Complete" the contribution request at the multiple contribution stage
    And I select the "Contributions Received" action at the contributions requested stage
    Then the case should be moved to the "Triage" stage

  @MPAMRegression1 @MPAMWorkflow
  Scenario Outline: User can complete the Draft (Contributions Requested) stage and select allocation actions upon completion
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "Contributions Requested"
    And I load the current case
    And I choose to "Complete" the contribution request at the multiple contribution stage
    And I select the "Contributions Received" action at the contributions requested stage
    And I select to "<retention>" the case that has been completed at the Draft-Contribution Request stage
    Then the case should be moved to the "Draft" stage
    And the case "<assertion>" be allocated to me in the summary
    Examples:
      | retention  | assertion  |
      | retain     | should     |
      | unallocate | should not |

  @Validation
  Scenario Outline: User tests the validation for the contribution request screens
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    Then I test the validation at the "<screen>" screen
    Examples:
      | screen                           |
      | Add Contribution Request         |
      | Contributions Requested          |
      | Contribution Request Fulfillment |
      | Unallocate case                  |

  Scenario: User can add multiple contribution requests to a case
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I add 5 contribution requests to the case and move the case to the Contribution Request stage
    And I load and claim the current case
    Then there are 5 contribution requests added to the case

  @MPAMRegression1 @MPAMWorkflow
  Scenario: User can escalate a case to workflow manager at the Triage-Contributions Requested stage
    And I create a "MPAM" case and move it to the "Triage" stage
    And I load and claim the current case
    And I send the Triage case to "Contributions Requested"
    And I load and claim the current case
    And I select the "Escalate to workflow manager" action at the contributions requested stage
    Then the case should be moved to the "Triage - Escalated (Contribution Requested)" stage

  @MPAMRegression1 @MPAMWorkflow
  Scenario: User can escalate a case to workflow manager at the Draft-Contributions Requested stage
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "Contributions Requested"
    And I load the current case
    And I select the "Escalate to workflow manager" action at the contributions requested stage
    Then the case should be moved to the "Draft - Escalated (Contribution Requested)" stage

  @MPAMRegression1 @MPAMWorkflow
  Scenario: User de-escalates a case returns it to the Triage-Contributions Requested stage
    And I create a "MPAM" case and move it to the "Triage" stage
    And I load and claim the current case
    And I send the Triage case to "Contributions Requested"
    And I load and claim the current case
    And I choose to "Complete" the contribution request at the multiple contribution stage
    And I select the "Escalate to workflow manager" action at the contributions requested stage
    And I load the current case
    And I select the "De-Escalate" action at the Triage-Escalated stage
    Then the case should be moved to the "Triage (Contribution Requested)" stage

  @MPAMRegression1 @MPAMWorkflow
  Scenario: User de-escalates a case returns it to the Draft-Contributions Requested stage
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "Contributions Requested"
    And I load the current case
    And I choose to "Complete" the contribution request at the multiple contribution stage
    And I select the "Escalate to workflow manager" action at the contributions requested stage
    And I load the current case
    And I select the "De-Escalate" action at the Draft-Escalated stage
    Then the case should be moved to the "Draft (Contribution Requested)" stage

  @MPAMRegression1 @MPAMWorkflow
  Scenario: User can add a case to a Campaign at the Triage-Contributions Requested stage
    And I create a "MPAM" case and move it to the "Triage" stage
    And I load and claim the current case
    And I send the Triage case to "Contributions Requested"
    And I load and claim the current case
    And I choose to "Complete" the contribution request at the multiple contribution stage
    And I select the "Put Case into Campaign" action at the contributions requested stage
    Then the case should be moved to the "Campaign" stage
    And the case is added to the correct Campaign


  @MPAMRegression1 @MPAMWorkflow
  Scenario: User can add a case to a Campaign at the Draft-Contributions Requested stage
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "Contributions Requested"
    And I load the current case
    And I choose to "Complete" the contribution request at the multiple contribution stage
    And I select the "Put Case into Campaign" action at the contributions requested stage
    Then the case should be moved to the "Campaign" stage
    And the case is added to the correct Campaign

  @MPAMRegression1
  Scenario: The earliest contribution request due date updates in workstacks when amended
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I add 3 contribution requests to the case and move the case to the Contribution Request stage
    Then the earliest due date of the contribution requests is displayed in workstacks
    And I navigate to the "Dashboard" page
    And I load the current case
    And I edit the due date of a contribution request
    Then the earliest due date of the contribution requests is displayed in workstacks