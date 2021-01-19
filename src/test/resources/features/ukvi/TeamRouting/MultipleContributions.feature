@MultipleContributions @TeamRouting @UKVI @UKVIWorkflow
Feature: Multiple Contributions

  Background:
    Given I log in to DECS
    
  Scenario: User can send a Triage case to the Triage (Contribution Requested) stage
    And I create a "MPAM" case and move it to the "Triage" stage
    And I load and claim the current case
    And I send the Triage case to "Contributions Requested"
    And I load and claim the current case
    Then the case should be moved to the "Triage (Contribution Requested)" stage

  Scenario: User can send a Draft case to the Draft (Contribution Requested) stage
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "Contributions Requested"
    And I load and claim the current case
    Then the case should be moved to the "Draft (Contribution Requested)" stage

 Scenario Outline: User can mark a contribution as complete or cancelled at the Contributions Requested stage
   And I create a "MPAM" case and move it to the "Triage" stage
   And I load and claim the current case
   And I send the Triage case to "Contributions Requested"
   And I load and claim the current case
   And I choose to "<action>" the contribution request at the multiple contribution stage
   Then the contribution request should be displayed as "<action>"
   Examples:
     | action    |
     | Complete  |
     | Cancel    |

 @UKVIRegression
 Scenario Outline: User can select actions for contributions at the Triage - Contributions Requested stage
   And I create a "MPAM" case and move it to the "Triage" stage
   And I load and claim the current case
   And I send the Triage case to "Contributions Requested"
   And I load and claim the current case
   And I choose to "<action>" the contribution request at the multiple contribution stage
   And I select the "Contributions Received" action at the contributions requested stage
   Then the case should be moved to the "Triage" stage
   Examples:
   | action    |
   | Complete  |
   | Cancel    |

 @UKVIRegression
 Scenario Outline: User can select actions for contributions at the Draft - Contributions Requested stage
   And I create a "MPAM" case and move it to the "Draft" stage
   And I load and claim the current case
   And I send the Draft case to "Contributions Requested"
   And I load and claim the current case
   And I choose to "<action>" the contribution request at the multiple contribution stage
   And I select the "Contributions Received" action at the contributions requested stage
   And I select to "Retain" the case that has been completed at the Draft-Contribution Request stage
   Then the case should be moved to the "Draft" stage
   Examples:
     | action    |
     | Complete  |
     | Cancel    |

 @Validation
 Scenario Outline: User tests the validation for the contribution request screens
   And I create a "MPAM" case and move it to the "Triage" stage
   And I load and claim the current case
   Then I test the validation at the "<screen>" screen
   Examples:
   | screen                             |
   | Add Contribution Request           |
   | Contributions Requested            |
   | Contribution Request Fulfillment   |

 Scenario: User can add multiple contribution requests to a case
   And I create a "MPAM" case and move it to the "Draft" stage
   And I load and claim the current case
   And I add 5 contribution requests to the case and move the case to the Contribution Request stage
   And I load and claim the current case
   Then there are 5 contribution requests added to the case

 @UKVIRegression
 Scenario: User can escalate a case to workflow manager at the Triage-Contributions Requested stage
   And I create a "MPAM" case and move it to the "Triage" stage
   And I load and claim the current case
   And I send the Triage case to "Contributions Requested"
   And I load and claim the current case
   And I choose to "Complete" the contribution request at the multiple contribution stage
   And I select the "Escalate to workflow manager" action at the contributions requested stage
   Then the case should be moved to the "Triage (Escalated)" stage

 @UKVIRegression
 Scenario: User can escalate a case to workflow manager at the Draft-Contributions Requested stage
   And I create a "MPAM" case and move it to the "Draft" stage
   And I load and claim the current case
   And I send the Draft case to "Contributions Requested"
   And I load and claim the current case
   And I choose to "Complete" the contribution request at the multiple contribution stage
   And I select the "Escalate to workflow manager" action at the contributions requested stage
   Then the case should be moved to the "Draft (Escalated)" stage

 @UKVIRegression
 Scenario: User de-escalates a case returns it to the Triage-Contributions Requested stage
   And I create a "MPAM" case and move it to the "Triage" stage
   And I load and claim the current case
   And I send the Triage case to "Contributions Requested"
   And I load and claim the current case
   And I choose to "Complete" the contribution request at the multiple contribution stage
   And I select the "Escalate to workflow manager" action at the contributions requested stage
   And I load and claim the current case
   And I de-escalate the Triage (Escalated) case
   Then the case should be moved to the "Triage (Contribution Requested)" stage

 @UKVIRegression
 Scenario: User de-escalates a case returns it to the Draft-Contributions Requested stage
   And I create a "MPAM" case and move it to the "Draft" stage
   And I load and claim the current case
   And I send the Draft case to "Contributions Requested"
   And I load and claim the current case
   And I choose to "Complete" the contribution request at the multiple contribution stage
   And I select the "Escalate to workflow manager" action at the contributions requested stage
   And I load and claim the current case
   And I de-escalate the Draft (Escalated) case
   Then the case should be moved to the "Draft (Contribution Requested)" stage

 @UKVIRegression
 Scenario: User can add a case to a Campaign at the Triage-Contributions Requested stage
   And I create a "MPAM" case and move it to the "Triage" stage
   And I load and claim the current case
   And I send the Triage case to "Contributions Requested"
   And I load and claim the current case
   And I choose to "Complete" the contribution request at the multiple contribution stage
   And I select the "Put Case into Campaign" action at the contributions requested stage
   Then the case should be moved to the "Campaign" stage

 @UKVIRegression
 Scenario: User can add a case to a Campaign at the Draft-Contributions Requested stage
   And I create a "MPAM" case and move it to the "Draft" stage
   And I load and claim the current case
   And I send the Draft case to "Contributions Requested"
   And I load and claim the current case
   And I choose to "Complete" the contribution request at the multiple contribution stage
   And I select the "Put Case into Campaign" action at the contributions requested stage
   Then the case should be moved to the "Campaign" stage

 @UKVIRegression
 Scenario: User can select to retain the case after completing the Draft (Contributions Requested) stage
   And I create a "MPAM" case and move it to the "Draft" stage
   And I load and claim the current case
   And I send the Draft case to "Contributions Requested"
   And I load and claim the current case
   And I choose to "Complete" the contribution request at the multiple contribution stage
   And I select the "Contributions Received" action at the contributions requested stage
   And I select to "Retain" the case that has been completed at the Draft-Contribution Request stage
   And the case should be visible in my workstack

 @UKVIRegression
 Scenario: User can select to unallocate the case after completing the Draft (Contributions Requested) stage
   And I create a "MPAM" case and move it to the "Draft" stage
   And I load and claim the current case
   And I send the Draft case to "Contributions Requested"
   And I load and claim the current case
   And I choose to "Complete" the contribution request at the multiple contribution stage
   And I select the "Contributions Received" action at the contributions requested stage
   And I select to "Unallocate" the case that has been completed at the Draft-Contribution Request stage
   And the case should not be visible in my workstack

 Scenario: The earliest contribution request due date updates in workstacks when amended
   And I create a "MPAM" case and move it to the "Draft" stage
   And I load and claim the current case
   And I add 3 contribution requests to the case and move the case to the Contribution Request stage
   Then the earliest due date of the contribution requests is displayed in workstacks
   And I navigate to the "home" page
   And I load the current case
   And I edit the due date of a contribution request
   Then the earliest due date of the contribution requests is displayed in workstacks