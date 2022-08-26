@ComplaintsEscalated @Complaints
Feature: Complaints Escalated

#     UKVI COMPLAINTS

  # HOCS-3076, HOCS-3028
  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can return a UKVI complaint case to Service Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service Escalated" stage
    And I load and claim the current case
    And I select to return the case to Triage
    Then the case should be moved to the "Service Triage" stage
    And the summary should display the owning team as "CCT Stage 1 Triage Team"
    And the read-only Case Details accordion should contain all case information entered during the "Service Escalated" stage

  # HOCS-3076, HOCS-3028
  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can send a UKVI complaint case to Service Draft stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service Escalated" stage
    And I load and claim the current case
    And I select to send the case to drafting
    Then the case should be moved to the "Service Draft" stage
    And the summary should display the owning team as "CCT Stage 1 Response Team"
    And the read-only Case Details accordion should contain all case information entered during the "Service Escalated" stage

  # Expected failure. Defect HOCS-4308 raised.
  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can return a UKVI complaint case to Ex-Gratia Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Ex-Gratia Escalate" stage
    And I load and claim the current case
    And I select to return the case to Triage
    Then the case should be moved to the "Ex-Gratia Triage" stage
    And the summary should display the owning team as "Ex-Gratia"
    And the read-only Case Details accordion should contain all case information entered during the "Ex-Gratia Escalate" stage

  # Expected failure. Defect HOCS-4308 raised.
  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can send a UKVI complaint case to Ex-Gratia Response Draft stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Ex-Gratia Escalate" stage
    And I load and claim the current case
    And I select to send the case to drafting
    Then the case should be moved to the "Ex-Gratia Response Draft" stage
    And the summary should display the owning team as "Ex-Gratia"
    And the read-only Case Details accordion should contain all case information entered during the "Ex-Gratia Escalate" stage

  # Expected failure. Defect HOCS-4308 raised.
  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can return a UKVI complaint case to Minor Misconduct Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Minor Misconduct Escalate" stage
    And I load and claim the current case
    And I select to return the case to Triage
    Then the case should be moved to the "Minor Misconduct Triage" stage
    And the summary should display the owning team as "Minor Misconduct"
    And the read-only Case Details accordion should contain all case information entered during the "Minor Misconduct Escalate" stage

  # Expected failure. Defect HOCS-4308 raised.
  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can send a UKVI complaint case to Minor Misconduct Response Draft stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Minor Misconduct Escalate" stage
    And I load and claim the current case
    And I select to send the case to drafting
    Then the case should be moved to the "Minor Misconduct Response Draft" stage
    And the summary should display the owning team as "Minor Misconduct"
    And the read-only Case Details accordion should contain all case information entered during the "Minor Misconduct Escalate" stage

  # HOCS-2870, HOCS-3096
  @COMPRegression @UKVIComplaints
  Scenario Outline: User can add and complete or cancel contributions to a UKVI complaint case as part of Service Escalated stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service Escalated" stage
    And I load and claim the current case
    And I add a "<contributionType>" contribution request
    And I "<action>" the contribution request
    Then the "<contributionType>" contribution request should be marked as "<action>"
    Examples:
      | contributionType | action   |
      | Complainant      | Complete |
      | Complainant      | Cancel   |
      | Business         | Complete |
      | Business         | Cancel   |

  @Validation @UKVIComplaints
  Scenario Outline: User tests the validation for a UKVI complaint case at the Service Escalated stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service Escalated" stage
    And I load and claim the current case
    And I trigger the "<errorType>" error message at the "Service Escalated" stage
    Then the "<errorType>" error message is displayed at the "Service Escalated" stage
    Examples:
      | errorType       |
      | Action Required |


#     UKVI COMPLAINTS STAGE 2

  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can return a UKVI stage 2 complaint case to Service Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Service Escalated" stage
    And I load and claim the current case
    And I select to return the case to Triage
    Then the case should be moved to the "Stage 2 Service Triage" stage
    And the summary should display the owning team as "Stage 2 CCT Triage Team"
    And the read-only Case Details accordion should contain all case information entered during the "Stage 2 Service Escalate" stage

  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can send a UKVI stage 2 complaint case to Service Draft stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Service Escalated" stage
    And I load and claim the current case
    And I select to send the case to drafting
    Then the case should be moved to the "Stage 2 Service Draft" stage
    And the summary should display the owning team as "Stage 2 CCT Response Team"
    And the read-only Case Details accordion should contain all case information entered during the "Stage 2 Service Escalate" stage


#     BF COMPLAINTS

  # HOCS-4055
  @ComplaintsWorkflow @BFRegression @BFComplaints
  Scenario: User can send a BF complaint case to Draft stage from Escalated
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF" case at the "Escalated to WFM" stage
    And I select to send the case to drafting
    Then the case should be moved to the "Draft" stage
    And the summary should display the owning team as "Border Force"

  # HOCS-4055
  @ComplaintsWorkflow @BFRegression @BFComplaints
  Scenario: User can send a BF complaint case to Triage stage from Escalated
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF" case at the "Escalated to WFM" stage
    And I select to return the case to Triage
    Then the case should be moved to the "Case Triage" stage
    And the summary should display the owning team as "Border Force"

  # HOCS-4055
  @BFRegression @BFComplaints
  Scenario Outline: User can add and complete or cancel contributions to a BF complaint cases as part of Escalated stage
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF" case at the "Escalated to WFM" stage
    And I add a "<contributionType>" contribution request
    And I "<action>" the contribution request
    Then the "<contributionType>" contribution request should be marked as "<action>"
    Examples:
      | contributionType | action   |
      | Complainant      | Complete |
      | Complainant      | Cancel   |
      | Business         | Complete |
      | Business         | Cancel   |


#     BF STAGE 2 COMPLAINTS

  @ComplaintsWorkflow @BFRegression @BFComplaints
  Scenario: User can send a BF stage 2 complaint case to Draft stage from Escalated
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF2" case at the "Escalated to WFM" stage
    And I select to send the case to drafting
    Then the case should be moved to the "Draft (Stage 2)" stage
    And the summary should display the owning team as "Border Force (Stage 2)"

  @ComplaintsWorkflow @BFRegression @BFComplaints
  Scenario: User can send a BF stage 2 complaint case to Triage stage from Escalated
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF2" case at the "Escalated to WFM" stage
    And I select to return the case to Triage
    Then the case should be moved to the "Case Triage (Stage 2)" stage
    And the summary should display the owning team as "Border Force (Stage 2)"

  @BFRegression @BFComplaints
  Scenario Outline: User can add and complete or cancel contributions to a BF stage 2 complaint cases as part of Escalated stage
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF2" case at the "Escalated to WFM" stage
    And I add a "<contributionType>" contribution request
    And I "<action>" the contribution request
    Then the "<contributionType>" contribution request should be marked as "<action>"
    Examples:
      | contributionType | action   |
      | Complainant      | Complete |
      | Complainant      | Cancel   |
      | Business         | Complete |
      | Business         | Cancel   |


    #    POGR COMPLAINTS

  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario Outline: User can send a POGR complaint case to Draft stage from Escalated
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR" case with "<businessArea>" as the Business Area at the "Escalated" stage
    And I select to return the case to Draft
    Then the case should be moved to the "Draft" stage
    And the POGR case should be assigned to the correct investigating team
    And the read-only Case Details accordion should contain all case information entered during the "Escalated" stage
    Examples:
      | businessArea |
      | HMPO         |
      | GRO          |

  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario Outline: User can send a POGR complaint case to Investigation stage from Escalated
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR" case with "<businessArea>" as the Business Area at the "Escalated" stage
    And I select to return the case to Investigation
    Then the case should be moved to the "Investigation" stage
    And the POGR case should be assigned to the correct investigating team
    And the read-only Case Details accordion should contain all case information entered during the "Escalated" stage
    Examples:
      | businessArea |
      | HMPO         |
      | GRO          |

  @POGRRegression @POGRComplaints
  Scenario Outline: User can add and complete or cancel contributions to a POGR complaint cases as part of Escalated stage
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR" case at the "Escalated" stage
    And I add a "<contributionType>" contribution request
    And I "<action>" the contribution request
    Then the "<contributionType>" contribution request should be marked as "<action>"
    Examples:
      | contributionType | action   |
      | Complainant      | Complete |
      | Complainant      | Cancel   |
      | Business         | Complete |
      | Business         | Cancel   |
