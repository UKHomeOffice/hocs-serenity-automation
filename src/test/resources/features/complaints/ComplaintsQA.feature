@ComplaintsQA @Complaints
Feature: Complaints QA

#     UKVI COMPLAINTS

  # HOCS-3695
  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can accept the response and send a UKVI complaint case to Service Send stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service QA" stage
    And I load and claim the current case
    When I "accept" the response to the complaint at the QA stage
    Then the case should be moved to the "Service Send" stage
    And the summary should display the owning team as "CCT Stage 1 Response Team"
    And the read-only Case Details accordion should contain all case information entered during the "Service QA" stage

  # HOCS-3039
  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can reject the response and send a UKVI complaint case back to Service Draft stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service QA" stage
    And I load and claim the current case
    When I "reject" the response to the complaint at the QA stage
    Then the case should be moved to the "Service Draft" stage
    And the summary should display the owning team as "CCT Stage 1 Response Team"
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case
    And the read-only Case Details accordion should contain all case information entered during the "Service QA" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can accept the response and send a UKVI complaint case to Ex-Gratia Send stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Ex-Gratia QA" stage
    And I load and claim the current case
    And I "accept" the response to the complaint at the QA stage
    Then the case should be moved to the "Ex-Gratia Send" stage
    And the summary should display the owning team as "Ex-Gratia"
    And the read-only Case Details accordion should contain all case information entered during the "Ex-Gratia QA" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can reject the response and send a UKVI complaint case back to Ex-Gratia Response Draft stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Ex-Gratia QA" stage
    And I load and claim the current case
    And I "reject" the response to the complaint at the QA stage
    Then the case should be moved to the "Ex-Gratia Response Draft" stage
    And the summary should display the owning team as "Ex-Gratia"
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case
    And the read-only Case Details accordion should contain all case information entered during the "Ex-Gratia QA" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can accept the response and send a UKVI complaint case to Minor Misconduct Send stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Minor Misconduct QA" stage
    And I load and claim the current case
    And I "accept" the response to the complaint at the QA stage
    Then the case should be moved to the "Minor Misconduct Send" stage
    And the summary should display the owning team as "Minor Misconduct"
    And the read-only Case Details accordion should contain all case information entered during the "Minor Misconduct QA" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can reject the response and send a UKVI complaint case back to Minor Misconduct Response Draft stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Minor Misconduct QA" stage
    And I load and claim the current case
    And I "reject" the response to the complaint at the QA stage
    Then the case should be moved to the "Minor Misconduct Response Draft" stage
    And the summary should display the owning team as "Minor Misconduct"
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case
    And the read-only Case Details accordion should contain all case information entered during the "Minor Misconduct QA" stage

  @Validation
  Scenario Outline: User tests the validation for a UKVI complaints case at the Service QA stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service QA" stage
    And I load and claim the current case
    And I trigger the "<errorType>" error message at the "Service QA" stage
    Then the "<errorType>" error message is displayed at the "Service QA" stage
    Examples:
      | errorType                 |
      | QA RESULT REQUIRED        |
      | REJECTION REASON REQUIRED |


#     UKVI COMPLAINTS STAGE 2

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can accept the response and send a UKVI stage 2 complaint case to Service Send stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Service QA" stage
    And I load and claim the current case
    When I "accept" the response to the complaint at the QA stage
    Then the case should be moved to the "Stage 2 Service Send" stage
    And the summary should display the owning team as "Stage 2 CCT Response Team"
    And the read-only Case Details accordion should contain all case information entered during the "Stage 2 Service QA" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can reject the response and send a UKVI stage 2 complaint case back to Service Draft stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Service QA" stage
    And I load and claim the current case
    When I "reject" the response to the complaint at the QA stage
    Then the case should be moved to the "Stage 2 Service Draft" stage
    And the summary should display the owning team as "Stage 2 CCT Response Team"
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case
    And the read-only Case Details accordion should contain all case information entered during the "Stage 2 Service QA" stage


#     BF COMPLAINTS

  # HOCS-4064
  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can accept the response at the QA stage and send a BF complaint case to the Send Draft Response stage
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF" case at the "QA" stage
    And I "accept" the response to the complaint at the QA stage
    Then the case should be moved to the "Send Draft Response" stage
    And the summary should display the owning team as "Border Force"
    And the read-only Case Details accordion should contain all case information entered during the "QA" stage

  # HOCS-4064, HOCS-4065
  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can reject the response at the QA stage and send a BF complaint case back to the Draft stage
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF" case at the "QA" stage
    And I "reject" the response to the complaint at the QA stage
    Then the case should be moved to the "Draft" stage
    And the summary should display the owning team as "Border Force"
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case
    And the read-only Case Details accordion should contain all case information entered during the "QA" stage


#     BF STAGE 2 COMPLAINTS

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can accept the response at the QA stage and send a BF Stage 2 complaint case to the Send Draft Response stage
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF2" case at the "QA" stage
    And I "accept" the response to the complaint at the QA stage
    Then the case should be moved to the "Send (Stage 2)" stage
    And the summary should display the owning team as "Border Force (Stage 2)"
    And the read-only Case Details accordion should contain all case information entered during the "QA (Stage 2)" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can reject the response at the QA stage and send a BF Stage 2 complaint case back to the Draft stage
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF2" case at the "QA" stage
    And I "reject" the response to the complaint at the QA stage
    Then the case should be moved to the "Draft (Stage 2)" stage
    And the summary should display the owning team as "Border Force (Stage 2)"
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case
    And the read-only Case Details accordion should contain all case information entered during the "QA (Stage 2)" stage