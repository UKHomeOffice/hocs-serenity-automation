@DCUDataInput @DCU
Feature: DCU Data Input

  Background:
    Given I am logged into "CS" as user "DCU_USER"

  @DCUWorkflow @DCURegression
  Scenario Outline: As a Data Input user, I want to be able to complete the Data Input stage, so the case can progress to the Markup stage
    When I get a "<caseType>" case at the "Data Input" stage
    And I complete the "Data Input" stage
    Then the case should be moved to the "Markup" stage
    And the summary should display the owning team as "<markupTeam>"
    And the read-only Case Details accordion should contain all case information entered during the "Data Input" stage
    Examples:
      | caseType | markupTeam                                       |
      | MIN      | Direct Communications Unit Central Drafting Team |
      | TRO      | Direct Communications Unit Central Drafting Team |
      | DTEN     | Transfers & No10 Team                            |

  @DCURegression
  Scenario: As a Data Input user, I want to be able to mark a MIN case as a potential Home Secretary Reply, so Markup users can prioritise the case
    When I get a "MIN" case at the "Data Input" stage
    And I complete the Data Input stage, selecting that the case is a potential Home Secretary Reply case
    And the "Direct Communications Unit Central Drafting Team" workstack should display a HS symbol next to the case reference

  @Navigation
  Scenario: DCU data entry user selects correspondence channel and date of correspondence
    When I get a "MIN" case at the "Data Input" stage
    And I fill all mandatory fields on the "Data Input" page with valid data
    And I click the "Continue" button
    Then the Add a correspondent link is displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  Scenario Outline: User checks that Home Secretary interest decision is properly displayed in summary tab
    When I get a "<caseType>" case at the "Data Input" stage
    And I select "<homeSecInterest>" for Home Secretary interest and complete the data input stage
    And I load the current case
    Then the Home Secretary interest decision should match the one displayed in the summary tab
    Examples:
      | caseType | homeSecInterest |
      | MIN      | Yes             |
      | MIN      | No              |
      | TRO      | Yes             |
      | TRO      | No              |

  @Validation
  Scenario Outline: DCU data entry user must enter valid dates on Data Input
    When I get a "MIN" case at the "Data Input" stage
    And I fill all mandatory fields on the "Data Input" page with valid data
    But I submit an invalid "<field>" date
    Then "Invalid date" error message is displayed
    Examples:
      | field                   |
      | Correspondence Sent     |
      | Correspondence Received |

  @Validation
  Scenario Outline: DCU data entry user cannot leave dates blank on Data Input
    When I get a "MIN" case at the "Data Input" stage
    And I fill all mandatory fields on the "Data Input" page with valid data
    But I do not enter a "<field>" date
    Then "<errorMessage>" error message is displayed
    Examples:
      | field                   | errorMessage           |
      | Correspondence Sent     | Correspondence Sent     |
      | Correspondence Received | Correspondence Received |

  @Validation
  Scenario Outline: User tests the validation at the DCU Data Input stage
    When I get a "<caseType>" case at the "Data Input" stage
    And I trigger the "<errorMessage>" error message at the "Data Input" stage
    Then the "<errorMessage>" error message is displayed at the "Data Input" stage
    Examples:
      | caseType | errorMessage                        |
      | MIN      | CORRESPONDENCE SENT DATE REQUIRED   |
      | MIN      | INBOUND CHANNEL REQUIRED            |
      | TRO      | COPY TO NUMBER 10 REQUIRED          |
      | TRO      | HOME SECRETARY INTEREST REQUIRED    |
      | DTEN     | PRIMARY CORRESPONDENT REQUIRED      |
      | DTEN     | DRAFTING DEADLINE REQUIRED          |
      | DTEN     | DISPATCH DEADLINE REQUIRED          |
      | MIN      | CORRESPONDENT TYPE MUST BE PROVIDED |
      | TRO      | MEMBER IS REQUIRED                  |
      | MIN      | CORRESPONDENT MUST HAVE TYPE        |
      | MIN      | CORRESPONDENT NAME REQUIRED         |
