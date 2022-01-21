@ComplaintsTriage @Complaints
Feature: Complaints Triage

#    Expected intermittent failure for Minor Misconduct example. Defect HOCS-4309 raised.
#  HOCS-2944, HOCS-2868
  @ComplaintsWorkflow @ComplaintsRegression
  Scenario Outline: User can transfer a case COMP case to CCH
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "<complaintType> Triage" stage
    And I load and claim the current case
    And I select to Transfer the complaint
    And I enter a reason for "CCH" transfer and continue
    Then the case should be moved to the "CCH" stage
    And the summary should display the owning team as "CCH Returned Cases"
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case
    And the read-only Case Details accordion should contain all case information entered during the "<complaintType> Triage" stage
    Examples:
      | complaintType    |
      | Service          |
      | Minor Misconduct |
      | Ex-Gratia        |

#    HOCS-2979, HOCS-3074, HOCS-2868, HOCS-2869, HOCS-3002, HOCS-2913
  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User completes the Service Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service Triage" stage
    And I load and claim the current case
    And I accept the case at "Service" Triage stage
    And I accept the previous Claim Category selection
    And I accept the previous Case Details selection
    And I submit details on the Triage Capture Reason page
    When I send the case to drafting
    Then the case should be moved to the "Service Draft" stage
    And the summary should display the owning team as "CCT Stage 1 Response Team"
    And the read-only Case Details accordion should contain all case information entered during the "Service Triage" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User completes the Ex-Gratia Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Ex-Gratia Triage" stage
    And I load and claim the current case
    And I accept the case at "Ex-Gratia" Triage stage
    And I select a "Ex-Gratia" Complaint Category
    And I accept the previous Claim Category selection
    And I accept the previous Case Details selection
    And I submit details on the Triage Capture Reason page
    And I send the case to drafting
    Then the case should be moved to the "Ex-Gratia Response Draft" stage
    And the summary should display the owning team as "Ex-Gratia"
    And the read-only Case Details accordion should contain all case information entered during the "Ex-Gratia Triage" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User completes the Minor Misconduct Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Minor Misconduct Triage" stage
    And I load and claim the current case
    And I accept the case at "Minor Misconduct" Triage stage
    And I select a "SERIOUS AND MINOR" Complaint Category
    And I select a Owning CSU
    And I click the "Continue" button
    And I accept the previous Case Details selection
    And I submit details on the Triage Capture Reason page
    When I send the case to drafting
    Then the case should be moved to the "Minor Misconduct Response Draft" stage
    And the summary should display the owning team as "Minor Misconduct"
    And the read-only Case Details accordion should contain all case information entered during the "Minor Misconduct Triage" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User completes the Triage stage for an IEDET case
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "Triage" stage
    And I load and claim the current case
    And I select the "Transferred to Third Party Supplier" action for an IEDET case at the Triage stage
    And I submit details on the Triage Capture Reason page
    Then the case should be moved to the "Draft" stage
    And the summary should display the owning team as "IE Detention"
    And the read-only Case Details accordion should contain all case information entered during the "Triage" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User completes the Triage stage for an SMC case
    Given I am logged into "CS" as user "SMC_USER"
    When I create a "SMC" case and move it to the "Triage" stage
    And I load and claim the current case
    And I accept the case at Triage stage
    And I enter details on PSU Reference page
    And I accept the previous Claim Category selection
    And I accept the previous Case Details selection
    And I submit details on the Triage Capture Reason page
    And I send the case to drafting
    And I load the current case
    And the summary should display the owning team as "Serious Misconduct"
    And the read-only Case Details accordion should contain all case information entered during the "Triage" stage

  @ComplaintsRegression @ComplaintsWorkflow
  Scenario: User completes the Case Triage stage for a BF case
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case and move it to the "Triage" stage
    And I load and claim the current case
    And I submit details on the Triage Capture Reason page
    And I select the "Ready for Drafting" action at Case Triage
    Then the case should be moved to the "DRAFT" stage
    And the summary should display the owning team as "Border Force"
    And the read-only Case Details accordion should contain all case information entered during the "Case Triage" stage

#    HOCS-3028
  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can escalate a case at Service Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service Triage" stage
    And I load and claim the current case
    And I accept the case at "Service" Triage stage
    And I accept the previous Claim Category selection
    And I accept the previous Case Details selection
    And I submit details on the Triage Capture Reason page
    When I escalate the case to WFM at Service Triage stage
    Then the case should be moved to the "Service Escalated" stage
    And the summary should display the owning team as "CCT Stage 1 Escalated"
    And an Escalation note should be visible in the timeline showing the submitted reason for the cases escalation
    And the read-only Case Details accordion should contain all case information entered during the "Service Triage" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can escalate a case at Ex-Gratia Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Ex-Gratia Triage" stage
    And I load and claim the current case
    And I accept the case at "Ex-Gratia" Triage stage
    And I select a "Ex-Gratia" Complaint Category
    And I accept the previous Claim Category selection
    And I accept the previous Case Details selection
    And I submit details on the Triage Capture Reason page
    And I escalate the case to WFM at Service Triage stage
    Then the case should be moved to the "Ex-Gratia Escalate" stage
    And the summary should display the owning team as "Ex-Gratia"
    And an Escalation note should be visible in the timeline showing the submitted reason for the cases escalation

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can escalate a case at Minor Misconduct Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Minor Misconduct Triage" stage
    And I load and claim the current case
    And I accept the case at "Minor Misconduct" Triage stage
    And I select a "SERIOUS AND MINOR" Complaint Category
    And I select a Owning CSU
    And I click the "Continue" button
    And I accept the previous Case Details selection
    And I submit details on the Triage Capture Reason page
    And I escalate the case to WFM at Service Triage stage
    Then the case should be moved to the "Minor Misconduct Escalate" stage
    And the summary should display the owning team as "Minor Misconduct"
    And an Escalation note should be visible in the timeline showing the submitted reason for the cases escalation

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User is able to escalate a BF case to workflow manager at the Case Triage stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case and move it to the "Triage" stage
    And I load and claim the current case
    And I submit details on the Triage Capture Reason page
    And I select the "Escalate to WFM" action at Case Triage
    Then the case should be moved to the "Escalate" stage
    And the summary should display the owning team as "Border Force"
    And an Escalation note should be visible in the timeline showing the submitted reason for the cases escalation
    And the read-only Case Details accordion should contain all case information entered during the "Case Triage" stage

#    HOCS-3026
  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can hard close a case at Service Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service Triage" stage
    And I load and claim the current case
    And I accept the case at "Service" Triage stage
    And I accept the previous Claim Category selection
    And I accept the previous Case Details selection
    And I submit details on the Triage Capture Reason page
    When I select to complete the case at Triage
    And I select a Close Reason
    And I enter a completion note at Service Triage
    And I click the "Complete case" button
    Then the case should be closed
    And a Case closure note should be visible in the timeline showing the submitted reason for closing the case
    And the read-only Case Details accordion should contain all case information entered during the "Service Triage" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can hard close a case at Ex-Gratia Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Ex-Gratia Triage" stage
    And I load and claim the current case
    And I accept the case at "Ex-Gratia" Triage stage
    And I select a "Ex-Gratia" Complaint Category
    And I accept the previous Claim Category selection
    And I accept the previous Case Details selection
    And I submit details on the Triage Capture Reason page
    When I select to complete the case at Triage
    And I select a Close Reason
    And I enter a completion note at Service Triage
    And I click the "Complete case" button
    Then the case should be closed
    And a Case closure note should be visible in the timeline showing the submitted reason for closing the case
    And the read-only Case Details accordion should contain all case information entered during the "Ex-Gratia Triage" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can hard close a case at Minor Misconduct Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Minor Misconduct Triage" stage
    And I load and claim the current case
    And I accept the case at "Minor Misconduct" Triage stage
    And I select a "SERIOUS AND MINOR" Complaint Category
    And I select a Owning CSU
    And I click the "Continue" button
    And I accept the previous Case Details selection
    And I submit details on the Triage Capture Reason page
    When I select to complete the case at Triage
    And I select a Close Reason
    And I enter a completion note at Service Triage
    And I click the "Complete case" button
    Then the case should be closed
    And a Case closure note should be visible in the timeline showing the submitted reason for closing the case
    And the read-only Case Details accordion should contain all case information entered during the "Minor Misconduct Triage" stage

  @ComplaintsRegression
  Scenario: User can close an IEDET case at the Triage stage
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "Triage" stage
    And I load and claim the current case
    And I select the "No Further Consideration" action for an IEDET case at the Triage stage
    Then the case should be closed

  @ComplaintsRegression
  Scenario: User can hard close a BF case at the Triage stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case and move it to the "Triage" stage
    And I load and claim the current case
    And I submit details on the Triage Capture Reason page
    And I select the "Complete the Case" action at Case Triage
    And I click the "Continue" button
    And I enter a reason for closing the case
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Case Triage" stage

#    Expected failure. Defect HOCS-3980 raised.
  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can transfer a SMC case from Triage to CCH
    Given I am logged into "CS" as user "SMC_USER"
    When I create a "SMC" case and move it to the "Triage" stage
    And I load and claim the current case
    And I select to Transfer the complaint
    And I enter a reason for "CCH" transfer and continue
    Then the case should be closed

#    Expected failure. Defect HOCS-3980 raised.
  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can transfer a SMC case from Triage to IEDET
    Given I am logged into "CS" as user "SMC_USER"
    When I create a "SMC" case and move it to the "Triage" stage
    And I load and claim the current case
    And I select to Transfer the complaint
    And I enter a reason for "IE Detention" transfer and continue
    Then the case should be closed

#    HOCS-2870, HOCS-3096, HOCS-3022
  @ComplaintsRegression
  Scenario Outline: User can add and complete or cancel contributions as part of Service Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service Triage" stage
    And I load and claim the current case
    And I accept the case at "Service" Triage stage
    And I accept the previous Claim Category selection
    And I accept the previous Case Details selection
    And I submit details on the Triage Capture Reason page
    And I add a "<contributionType>" contribution request
    And I "<action>" the contribution request
    Then the "<contributionType>" contribution request should be marked as "<action>"
    Examples:
      | contributionType | action   |
      | Complainant      | Complete |
      | Business         | Cancel   |
      | Complainant      | Complete |
      | Business         | Cancel   |

#    HOCS-3103
  @ComplaintsRegression
  Scenario: User can tell if a contribution is overdue on the Triage Contributions page
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service Triage" stage
    And I load and claim the current case
    And I accept the case at "Service" Triage stage
    And I accept the previous Claim Category selection
    And I accept the previous Case Details selection
    And I submit details on the Triage Capture Reason page
    And I add a "complainant" contribution request with a due date in the past
    Then the "complainant" contribution request should be marked as "overdue"
    And the overdue contribution request should be highlighted

#    HOCS-2979
  @ComplaintsRegression
  Scenario: User can select that a Letter of Authority is required for this complaint
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service Triage" stage
    And I load and claim the current case
    And I accept the case at "Service" Triage stage
    And I accept the previous Claim Category selection
    And I accept the previous Case Details selection
    And I select that a Letter of Authority is required
    And I click the "Continue" button
    And I can mark that the LoA was received and enter the LoA date
    And I send the case to drafting
    And I load the current case
    And the read-only Case Details accordion should contain all case information entered during the "Service Triage" stage

  @Validation
  Scenario Outline: User tests the validation at the Service Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service Triage" stage
    And I load and claim the current case
    And I trigger the "<errorType>" error message at the "Service Triage" stage
    Then the "<errorType>" error message is displayed at the "Service Triage" stage
    Examples:
      | errorType                                         |
      | CAN YOUR TEAM RESPOND TO COMPLAINT REQUIRED       |
      | REASON FOR TRANSFER REQUIRED                      |
      | TRANSFER TO REQUIRED                              |
      | BUSINESS AREA REQUIRED                            |
      | ENQUIRY REASON REQUIRED                           |
      | IS LETTER OF AUTHORITY REQUIRED RESPONSE REQUIRED |
      | ACTION REQUIRED                                   |
      | ESCALATION REASON REQUIRED                        |
      | COMPLETE CASE NOTE REQUIRED                       |
      | COMPLETE CASE PERMANENTLY RESPONSE REQUIRED       |