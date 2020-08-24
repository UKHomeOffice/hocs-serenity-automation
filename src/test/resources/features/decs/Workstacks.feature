@Workstacks @DECS
Feature: Workstacks

  Background:
    Given I log in to DECS

  @SmokeTests
  Scenario: User creates a case and allocates to another user
    And I create a new case and view it in the Performance and Process team workstack
    When I allocate the current case to "CASEY"
    Then the owner field should display "CASEY"

  @SmokeTests
  Scenario: User creates a case and assigns it to themselves from the workstack
    And I create a new case and view it in the Performance and Process team workstack
    When I assign this case to me, and check if it has been correctly allocated

  @SmokeTests
  Scenario: User creates 3 cases and allocates these cases to another User
    And I create three cases, and view them in performance and process workstack
    Then I assign these three cases to "CASEY"
    And I check that the three cases created have been correctly assigned to "CASEY"

  @SmokeTests
  Scenario: User creates and allocates 3 cases to another User, then unallocates these cases
    And I create three cases, and assign them to "CASEY"
    Then I view these cases in Performance and Process workstack, and unallocate from "CASEY"
    And I then check whether the correct cases have been unallocated

  @SmokeTests
  Scenario: User creates a Ministerial case and uses the MIN filter card to view it in the Performance and Process workstack
    Given I create a single "MIN" case and return to the dashboard
    When I enter the Performance and Process team workstack and narrow down the visible cases using the "MIN" filter card
    Then only "MIN" cases should be visible
    And the created case should be visible in the workstack

  @SmokeTests
  Scenario: User creates a Number 10 case and uses the DTEN filter card to view it in the Transfers and No10 team workstack
    Given I create a single "DTEN" case and return to the dashboard
    When I enter the Transfers and No10 team workstack and narrow down the visible cases using the TRO filter card
    Then only "DTEN" cases should be visible
    And the created case should be visible in the workstack

  @SmokeTests
  Scenario: User creates a Treat Official case and uses the TRO filter card to view it in the Performance and Process
  workstack
    Given I create a single "TRO" case and return to the dashboard
    When I enter the Performance and Process team workstack and narrow down the visible cases using the "TRO" filter card
    Then only "TRO" cases should be visible
    And the created case should be visible in the workstack

  @OtherTests
  Scenario Outline: User is able to order MPAM workstack columns
    Given I create a "MPAM" case and move it to the "Triage" stage
    When I navigate to the "Triage" workstack and order the "<column>" column from "<order>"
    Then the "<column>" column is ordered from "<order>"
    Examples:
      | column        | order             |
      | Reference     | Lowest to Highest |
      | Reference     | Highest to Lowest |
      | Current Stage | Lowest to Highest |
      | Current Stage | Highest to Lowest |
      | Owner         | Lowest to Highest |
      | Owner         | Highest to Lowest |
      | Deadline      | Lowest to Highest |
      | Deadline      | Highest to Lowest |
      | Urgency       | Lowest to Highest |
      | Urgency       | Highest to Lowest |
      | Days          | Lowest to Highest |
      | Days          | Highest to Lowest |

  @OtherTests
  Scenario Outline: User is able to order columns in my cases
    Given I navigate to my cases and order the "<column>" column from "<order>"
    Then the "<column>" column is ordered from "<order>"
    Examples:
      | column        | order             |
      | Reference     | Lowest to Highest |
      | Reference     | Highest to Lowest |
      | Ref Type      | Lowest to Highest |
      | Ref Type      | Highest to Lowest |
      | Business Area | Lowest to Highest |
      | Business Area | Highest to Lowest |
      | Current Stage | Lowest to Highest |
      | Current Stage | Highest to Lowest |
      | Deadline      | Lowest to Highest |
      | Deadline      | Highest to Lowest |
      | Urgency       | Lowest to Highest |
      | Urgency       | Highest to Lowest |
      | Days          | Lowest to Highest |
      | Days          | Highest to Lowest |

  @OtherTests
  Scenario Outline: User is able to order search results
    Given I search for active MPAM cases and order the "<column>" column from "<order>"
    Then the "<column>" column is ordered from "<order>"
    Examples:
      | column        | order             |
      | Reference     | Lowest to Highest |
      | Reference     | Highest to Lowest |
      | Current Stage | Lowest to Highest |
      | Current Stage | Highest to Lowest |
      | Owner         | Lowest to Highest |
      | Owner         | Highest to Lowest |
      | Team          | Lowest to Highest |
      | Team          | Highest to Lowest |
      | Deadline      | Lowest to Highest |
      | Deadline      | Highest to Lowest |

  @SmokeTests
  Scenario: User is able to see a highlighted deadline on an MPAM case that is 5 days from its deadline datee
    Given I create a single "MPAM" case with the correspondence received date set 15 workdays ago
    And I view the MPAM case in the appropriate "Creation" stage workstack
    Then the case deadline "should" be highlighted

  @OtherTests
  Scenario: User is unable to see a highlighted deadline on an MPAM case that is 6 days from its deadline date
    Given I create a single "MPAM" case with the correspondence received date set 14 workdays ago
    And I view the MPAM case in the appropriate "Creation" stage workstack
    Then the case deadline "should not" be highlighted

  @MPAMSmokeTests
  Scenario: User adds a case to a Campaign and can view the case in the correct workstack
    Given I create a "MPAM" case and move it to the "Triage" stage
    And I load and claim the current case
    And I move the case into a Campaign from the "Triage" stage
    And I view the MPAM case in the appropriate "Campaign" stage workstack
    Then the created case should be visible in the workstack

  Scenario Outline: User is able to order the Campaign workstack column
    Given I create a "MPAM" case and move it to the "Triage" stage
    And I load and claim the current case
    And I move the case into a Campaign from the "Triage" stage
    And I navigate to the "Campaign" workstack and order the "Campaign" column from "<order>"
    Then the "Campaign" column is ordered from "<order>"
    Examples:
    | order             |
    | Lowest to Highest |
    | Highest to Lowest |