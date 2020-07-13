@AccordionMPAM
Feature: MPAM Case Details Accordion

  Background:
    Given I log in to DECS

  @SmokeTests
  Scenario Outline: User completes a stage and checks that the information entered is correct in the case details accordion
    And I create a "MPAM" case and move it to the "<stage>" stage
    And I load the current case
    Then the "<stageAccordion>" accordion contains all of the correct information previously input
    Examples:
    |stage |stageAccordion|
    |Triage|Creation      |
    |Draft |Triage        |
    |QA    |Draft         |

  Scenario Outline: User completes the creation stage and the information entered is displayed in the case details accordion
    And I create a "MPAM" case and move it to the "Triage" stage
    And I load the current case
    Then the "Creation" accordion in case details should display the correct information for "<infoType>"
    Examples:
    |infoType             |
    |Business Area        |
    |Reference Type       |
    |Urgency              |
    |Channel Received     |
    |Primary Correspondent|
    |Received Date        |

  Scenario Outline: User completes the triage stage and the information entered is displayed in the case details accordion
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load the current case
    Then the "Triage" accordion in case details should display the correct information for "<infoType>"
    Examples:
    |infoType       |
    |Enquiry Subject|
    |Enquiry Reason |
    |Business Unit  |

  Scenario: User completes the draft stage and the information entered is displayed in the case details accordion
    And I create a "MPAM" case and move it to the "QA" stage
    And I load the current case
    Then the "Draft" accordion in case details should display the correct information for "Response Channel"

  Scenario Outline: User changes the business area of the case
    And I create a MPAM case  with "UKVI" as the Business Area and "Ministerial" as the Reference Type and move it to the "Triage" stage
    And I load and claim the current case
    And I change the business area of the case to "<businessArea>"
    Then the case should have changed to the "<businessArea>" business area
    Examples:
    |businessArea |
    |UKVI         |
    |BF           |
    |IE           |
    |EUSS         |
    |HMPO         |
    |Windrush     |
    |Coronavirus  |