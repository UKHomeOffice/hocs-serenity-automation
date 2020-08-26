@MPAMAccordion @MPAM
Feature: MPAM Case Details Accordion

  Background:
    Given I log in to DECS

  @MPAMSmokeTests
  Scenario Outline: User completes a stage and checks that the information entered is correct in the case details accordion
    And I create a "MPAM" case and move it to the "<stage>" stage
    And I load the current case
    Then the "<stageAccordion>" accordion contains all of the correct information previously input
    Examples:
      | stage  | stageAccordion |
      | Triage | Creation       |
      | Draft  | Triage         |
      | QA     | Draft          |

  Scenario Outline: User completes the creation stage and the information entered is displayed in the case details accordion
    And I create a "MPAM" case and move it to the "Triage" stage
    And I load the current case
    Then the "Creation" accordion in case details should display the correct information for "<infoType>"
    Examples:
      | infoType              |
      | Business Area         |
      | Reference Type        |
      | Urgency               |
      | Channel Received      |
      | Primary Correspondent |
      | Received Date         |

  Scenario Outline: User completes the triage stage and the information entered is displayed in the case details accordion
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load the current case
    Then the "Triage" accordion in case details should display the correct information for "<infoType>"
    Examples:
      | infoType        |
      | Enquiry Subject |
      | Enquiry Reason  |
      | Business Unit   |

  Scenario: User completes the draft stage and the information entered is displayed in the case details accordion
    And I create a "MPAM" case and move it to the "QA" stage
    And I load the current case
    Then the "Draft" accordion in case details should display the correct information for "Response Channel"

  @MPAMSmokeTests
  Scenario Outline: User changes the business area of the case
    And I create a MPAM case  with "UKVI" as the Business Area and "Ministerial" as the Reference Type and move it to the "Triage" stage
    And I load and claim the current case
    And I change the business area of the case to "<businessArea>"
    Then the case should have changed to the "<businessArea>" business area
    Examples:
      | businessArea |
      | UKVI         |
      | BF           |
      | IE           |
      | EUSS         |
      | HMPO         |
      | Windrush     |
      | Coronavirus  |

  @OtherTests
  Scenario Outline: User checks that the change business area hypertext is displayed at the correct stages
    And I create a "MPAM" case and move it to the "<stage>" stage
    And I load and claim the current case
    Then the change business area hypertext "<is/isn't>" displayed at "<stage>"
    Examples:
      | stage             | is/isn't |
      | Triage            | is       |
      | Draft             | is       |
      | QA                | isn't    |
      | Private Office    | isn't    |
      | Awaiting Dispatch | isn't    |

  @Validation
  Scenario Outline: User triggers an error message at the change business area screen
    And I create a "MPAM" case and move it to the "Triage" stage
    And I load and claim the current case
    Then I trigger the "<errorMessage>" error message and it is displayed at the change business area screen
    Examples:
      | errorMessage           |
      | Business Unit required |
      | Actions required       |

  Scenario Outline: User can change the reference type of a case
    And I create a "MPAM" case and move it to the "Triage" stage
    And I load and claim the current case
    And I change the reference type of the case to "<reason>"
    Then the reference type that is displayed is correct
    Examples:
    | reason            |
    | Convert the Case  |
    | Correct an Error  |

  Scenario: User can change the reference type of a case and check the case is allocated to the correct team
    And I create a "MPAM" case and move it to the "Triage" stage
    And I load and claim the current case
    And I change the reference type of the case to "Convert the Case"
    Then the case should be in the correct MPAM "Triage" team workstack