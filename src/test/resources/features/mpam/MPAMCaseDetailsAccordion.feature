@AccordionMPAM
Feature: MPAM Case Details Accordion

  Background:
    Given I log in to DECS

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