@Workstacks @CS
Feature: Workstacks

  Background:
    Given I am logged into "CS" as user "DECS_USER"

  @CSRegression @CSE2ETests
  Scenario: User creates a case and allocates to another user using the top allocation dropdown menu
    And I create a new case and view it in the Performance and Process team workstack
    When I allocate the current case to "CASEY"
    Then the owner field should display "CASEY"

  @CSRegression @CSE2ETests
  Scenario: User creates a case and allocates to another user using the bottom allocation dropdown menu
    And I create a new case and view it in the Performance and Process team workstack
    When I allocate the current case to "CASEY" using the bottom allocation dropdown menu
    Then the owner field should display "CASEY"

  @CSRegression @CSE2ETests
  Scenario: User creates a case and assigns it to themselves from the workstack
    And I create a new case and view it in the Performance and Process team workstack
    When I assign this case to me, and check if it has been correctly allocated

  @CSRegression @CSE2ETests
  Scenario: User creates 3 cases and allocates these cases to another User
    And I create three cases, and view them in performance and process workstack
    Then I assign these three cases to "CASEY"
    And I check that the three cases created have been correctly assigned to "CASEY"

  @CSRegression
  Scenario: User creates and allocates 3 cases to another User then unallocates these cases
    And I create three cases, and assign them to "CASEY"
    Then I view these cases in Performance and Process workstack, and unallocate from "CASEY"
    And I then check whether the correct cases have been unallocated

  @CSRegression @CSE2ETests
  Scenario: User only sees cases of the selected Workflow after clicking a Workflow filter card
    And I enter a random team workstack from the dashboard
    When I select a Workflow filter card
    Then only cases of that Workflow should be displayed in the workstack

  @CSRegression @CSE2ETests
  Scenario: User only sees cases at the selected stage after clicking a stage filter card
    And I enter a random team workstack from the dashboard
    When I select a Workflow filter card
    And I select a Stage filter card
    Then only cases at that Stage should be displayed in the workstack

  Scenario Outline: User is able to order MPAM workstack columns
    And I create a "MPAM" case and move it to the "Triage" stage
    When I navigate to the "Triage" workstack and order the "<column>" column from "<order>"
    Then the "<column>" column is ordered from "<order>"
    Examples:
      | column            | order             |
      | Reference         | Lowest to Highest |
      | Reference         | Highest to Lowest |
      | Current Stage     | Lowest to Highest |
      | Current Stage     | Highest to Lowest |
      | Owner             | Lowest to Highest |
      | Owner             | Highest to Lowest |
      | Minister Sign Off | Lowest to Highest |
      | Minister Sign Off | Highest to Lowest |
      | Deadline          | Lowest to Highest |
      | Deadline          | Highest to Lowest |
      | Urgency           | Lowest to Highest |
      | Urgency           | Highest to Lowest |
      | Days              | Lowest to Highest |
      | Days              | Highest to Lowest |
      | Rejected          | Lowest to Highest |
      | Rejected          | Highest to Lowest |


  Scenario Outline: User is able to order columns in my cases
    When I navigate to my cases and order the "<column>" column from "<order>"
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


  Scenario Outline: User is able to order search results
    When I search for active MPAM cases and order the "<column>" column from "<order>"
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

  Scenario Outline: User is able to order Telephone Surgery Official engagement column
    When I navigate to the "MTS Team" workstack and order the "<column>" column from "<order>"
    Then the "<column>" column is ordered from "<order>"
    Examples:
      | column                                | order             |
      | Telephone Surgery Official Engagement | Lowest to Highest |
      | Telephone Surgery Official Engagement | Highest to Lowest |

  @CSRegression
  Scenario: User is able to see a red highlighted deadline on an MPAM case that is 5 days from its deadline date
    When I create a single "MPAM" case with the correspondence received date set 15 workdays ago
    And I view the MPAM case in the appropriate "Creation" stage workstack
    Then the case deadline "should" be highlighted

  Scenario: User is unable to see a red highlighted deadline on an MPAM case that is 6 days from its deadline date
    When I create a single "MPAM" case with the correspondence received date set 14 workdays ago
    And I view the MPAM case in the appropriate "Creation" stage workstack
    Then the case deadline "should not" be highlighted

  @MPAMRegression2
  Scenario: User adds a case to a Campaign and can view the case in the correct workstack
    And I create a "MPAM" case and move it to the "Triage" stage
    And I load and claim the current case
    When I move the case into a Campaign from the "Triage" stage
    And I view the MPAM case in the appropriate "Campaign" stage workstack
    Then the created case should be visible in the workstack

  Scenario Outline: User is able to order the Campaign workstack column
    And I create a "MPAM" case and move it to the "Triage" stage
    And I load and claim the current case
    When I move the case into a Campaign from the "Triage" stage
    And I navigate to the "Campaign" workstack and order the "Campaign" column from "<order>"
    Then the "Campaign" column is ordered from "<order>"
    Examples:
      | order             |
      | Lowest to Highest |
      | Highest to Lowest |

  @MPAMRegression2
  Scenario Outline: User can select to take the next unallocated case from the team workstack
    And I create a high priority MPAM case and move it to the "<stage>" stage
    When I view the MPAM case in the appropriate "<stage>" stage workstack
    And I record the highest priority cases in the workstack
    And I select to take the next unallocated case from the team workstack
    Then one of the highest priority unallocated cases is loaded and allocated to the user
    Examples:
      | stage  |
      | Triage |
      | Draft  |

  @DCURegression
  Scenario Outline: DCU User sees the required information when viewing a workstack
    Given I switch to user "DCU_USER"
    And I enter a "<workstack>" workstack
    Then the "<workstack>" workstack should contain only the expected columns
    Examples:
      | workstack    |
      | DCU My Cases |
      | DCU Team     |

  @MPAMRegression2
  Scenario Outline: MPAM User sees the required information when viewing a workstack
    Given I switch to user "MPAM_USER"
    And I enter a "<workstack>" workstack
    Then the "<workstack>" workstack should contain only the expected columns
    Examples:
      | workstack     |
      | MPAM My Cases |
      | MPAM Creation |
      | MPAM Draft    |
      | MPAM Triage   |
      | MPAM Campaign |
      | MTS Team      |