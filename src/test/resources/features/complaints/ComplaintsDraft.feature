@ComplaintsDraft @Complaints
Feature: Complaints Draft


#     UKVI COMPLAINTS

  # HOCS-3695
  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can send a UKVI complaint case to Service Send stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service Draft" stage
    And I load and claim the current case
    And I add a "DRAFT" type document to the case
    And I select the "Response is ready to send" action at the Service Draft stage
    Then the case should be moved to the "Service Send" stage
    And the summary should display the owning team as "CCT Stage 1 Response Team"
    And the read-only Case Details accordion should contain all case information entered during the "Service Draft" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can send a UKVI complaint case to Ex-Gratia Send stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Ex-Gratia Response Draft" stage
    And I load and claim the current case
    And I add a "DRAFT" type document to the case
    And I select the "Response is ready to send" action at the Service Draft stage
    Then the case should be moved to the "Ex-Gratia Send" stage
    And the summary should display the owning team as "Ex-Gratia"
    And the read-only Case Details accordion should contain all case information entered during the "Ex-Gratia Response Draft" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can send a UKVI complaint case to Minor Misconduct Send stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Minor Misconduct Response Draft" stage
    And I load and claim the current case
    And I add a "DRAFT" type document to the case
    And I select the "Response is ready to send" action at the Service Draft stage
    Then the case should be moved to the "Minor Misconduct Send" stage
    And the summary should display the owning team as "Minor Misconduct"
    And the read-only Case Details accordion should contain all case information entered during the "Minor Misconduct Response Draft" stage

  # HOCS-3695
  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can send a UKVI complaint case to Service QA stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service Draft" stage
    And I load and claim the current case
    And I add a "DRAFT" type document to the case
    And I select the "Send case to QA" action at the Service Draft stage
    Then the case should be moved to the "Service QA" stage
    And the summary should display the owning team as "CCT Stage 1 Response QA"
    And the read-only Case Details accordion should contain all case information entered during the "Service Draft" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can send a UKVI complaint case to Ex-Gratia QA stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Ex-Gratia Response Draft" stage
    And I load and claim the current case
    And I add a "DRAFT" type document to the case
    And I select the "Send case to QA" action at the Service Draft stage
    Then the case should be moved to the "Ex-Gratia QA" stage
    And the summary should display the owning team as "Ex-Gratia"
    And the read-only Case Details accordion should contain all case information entered during the "Ex-Gratia Response Draft" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can send a UKVI complaint case to Minor Misconduct QA stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Minor Misconduct Response Draft" stage
    And I load and claim the current case
    And I add a "DRAFT" type document to the case
    And I select the "Send case to QA" action at the Service Draft stage
    Then the case should be moved to the "Minor Misconduct QA" stage
    And the summary should display the owning team as "Minor Misconduct"
    And the read-only Case Details accordion should contain all case information entered during the "Minor Misconduct Response Draft" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User is able to escalate a UKVI complaint case to WFM at Service Draft stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service Draft" stage
    And I load and claim the current case
    And I escalate the case to WFM at Service Draft stage
    Then the case should be moved to the "Service Escalated" stage
    And the summary should display the owning team as "CCT Stage 1 Escalated"
    And an Escalation note should be visible in the timeline showing the submitted reason for the cases escalation
    And the read-only Case Details accordion should contain all case information entered during the "Service Draft" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User is able to escalate a UKVI complaint case to WFM at Ex-Gratia Response Draft stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Ex-Gratia Response Draft" stage
    And I load and claim the current case
    And I escalate the case to WFM at Service Draft stage
    Then the case should be moved to the "Ex-Gratia Escalate" stage
    And the summary should display the owning team as "Ex-Gratia"
    And an Escalation note should be visible in the timeline showing the submitted reason for the cases escalation
    And the read-only Case Details accordion should contain all case information entered during the "Ex-Gratia Response Draft" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User is able to escalate a UKVI complaint case to WFM at Minor Misconduct Response Draft stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Minor Misconduct Response Draft" stage
    And I load and claim the current case
    And I escalate the case to WFM at Service Draft stage
    Then the case should be moved to the "Minor Misconduct Escalate" stage
    And the summary should display the owning team as "Minor Misconduct"
    And an Escalation note should be visible in the timeline showing the submitted reason for the cases escalation
    And the read-only Case Details accordion should contain all case information entered during the "Minor Misconduct Response Draft" stage

  # HOCS-3076
  @Validation
  Scenario: User must upload a document at Service Draft stage for a UKVI complaint case
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service Draft" stage
    And I load and claim the current case
    And I select the "Response is ready to send" action at the Service Draft stage
    Then an error message is displayed as I have not uploaded a document

  @Validation
  Scenario Outline: User tests the validation for a UKVI complaint case at the Service Draft stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service Draft" stage
    And I load and claim the current case
    When I trigger the "<errorType>" error message at the "Service Draft" stage
    Then the "<errorType>" error message is displayed at the "Service Draft" stage
    Examples:
      | errorType                        |
      | PRIMARY DRAFT DOCUMENT REQUIRED  |
      | ACTION REQUIRED                  |
      | ESCALATION REASON                |


#     IEDET COMPLAINTS

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User completes the Draft stage for an IEDET complaint case
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "Draft" stage
    And I load and claim the current case
    And I click the "Proceed to recording outcome" button
    Then the case should be moved to the "Send" stage
    And the summary should display the owning team as "IE Detention"


#     SMC COMPLAINTS

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User completes the Draft stage for an SMC complaint case
    Given I am logged into "CS" as user "SMC_USER"
    When I create a "SMC" case and move it to the "Draft" stage
    And I load and claim the current case
    And I add a "DRAFT" type document to the case
    And I click the "Response Ready" button
    Then the case should be moved to the "Send" stage
    And the summary should display the owning team as "Serious Misconduct"


#     BF COMPLAINTS

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User completes the Draft stage for a BF complaint case
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF" case at the "Draft" stage
    And I add a "DRAFT" type document to the case
    And I select the "Response is Ready to Send" action at the Draft stage
    Then the case should be moved to the "Send draft response" stage
    And the summary should display the owning team as "Border Force"
    And the read-only Case Details accordion should contain all case information entered during the "Draft" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can send a BF complaint case to the QA stage from Draft
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF" case at the "Draft" stage
    And I add a "DRAFT" type document to the case
    And I select the "Send case to QA" action at the Draft stage
    Then the case should be moved to the "QA" stage
    And the summary should display the owning team as "Border Force"
    And the read-only Case Details accordion should contain all case information entered during the "Draft" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User is able to escalate a BF complaint case to WFM at Draft stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case and move it to the "Draft" stage
    And I load and claim the current case
    And I add a "DRAFT" type document to the case
    And I escalate the case to WFM at Draft stage
    Then the case should be moved to the "Escalated to WFM" stage
    And the summary should display the owning team as "Border Force"
    And an Escalation note should be visible in the timeline showing the submitted reason for the cases escalation
    And the read-only Case Details accordion should contain all case information entered during the "Draft" stage

#     BF STAGE 2 COMPLAINTS

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can send a BF stage 2 complaint case to the QA stage from Draft
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF2" case at the "Draft" stage
    And I add a "DRAFT" type document to the case
    And I select the "Send case to QA" action at the Draft stage
    Then the case should be moved to the "QA (Stage 2)" stage
    And the summary should display the owning team as "Border Force (Stage 2)"
    And the read-only Case Details accordion should contain all case information entered during the "Draft (Stage 2)" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User is able to escalate a BF stage 2 complaint case to WFM at Draft stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF2" case and move it to the "Draft" stage
    And I load and claim the current case
    And I add a "DRAFT" type document to the case
    And I escalate the case to WFM at Draft stage
    Then the case should be moved to the "Escalated to WFM (Stage 2)" stage
    And the summary should display the owning team as "Border Force (Stage 2)"
    And an Escalation note should be visible in the timeline showing the submitted reason for the cases escalation
    And the read-only Case Details accordion should contain all case information entered during the "Draft (Stage 2)" stage