@ComplaintsTriage @Complaints
Feature: Complaints Triage

#     UKVI COMPLAINTS

  # HOCS-2944, HOCS-2868
  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario Outline: User can transfer a Stage 1 UKVI complaint case back to CCH
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

  # HOCS-2979, HOCS-3074, HOCS-2868, HOCS-2869, HOCS-3002, HOCS-2913
  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User completes the Service Triage stage for a UKVI complaint case
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

  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User completes the Ex-Gratia Triage stage for a UKVI complaint case
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

  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User completes the Minor Misconduct Triage stage for a UKVI complaint case
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

  # HOCS-3028
  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can escalate a UKVI complaint case at Service Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service Triage" stage
    And I load and claim the current case
    And I accept the case at "Service" Triage stage
    And I accept the previous Claim Category selection
    And I accept the previous Case Details selection
    And I submit details on the Triage Capture Reason page
    And I escalate the case to WFM at Triage stage
    Then the case should be moved to the "Service Escalated" stage
    And the summary should display the owning team as "CCT Stage 1 Escalated"
    And an Escalation note should be visible in the timeline showing the submitted reason for the cases escalation
    And the read-only Case Details accordion should contain all case information entered during the "Service Triage" stage

  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can escalate a UKVI complaint case at Ex-Gratia Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Ex-Gratia Triage" stage
    And I load and claim the current case
    And I accept the case at "Ex-Gratia" Triage stage
    And I select a "Ex-Gratia" Complaint Category
    And I accept the previous Claim Category selection
    And I accept the previous Case Details selection
    And I submit details on the Triage Capture Reason page
    And I escalate the case to WFM at Triage stage
    Then the case should be moved to the "Ex-Gratia Escalate" stage
    And the summary should display the owning team as "Ex-Gratia"
    And an Escalation note should be visible in the timeline showing the submitted reason for the cases escalation

  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can escalate a UKVI complaint case at Minor Misconduct Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Minor Misconduct Triage" stage
    And I load and claim the current case
    And I accept the case at "Minor Misconduct" Triage stage
    And I select a "SERIOUS AND MINOR" Complaint Category
    And I select a Owning CSU
    And I click the "Continue" button
    And I accept the previous Case Details selection
    And I submit details on the Triage Capture Reason page
    And I escalate the case to WFM at Triage stage
    Then the case should be moved to the "Minor Misconduct Escalate" stage
    And the summary should display the owning team as "Minor Misconduct"
    And an Escalation note should be visible in the timeline showing the submitted reason for the cases escalation

  # HOCS-3026
  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can hard close a UKVI complaint case at Service Triage stage
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

  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can hard close a UKVI complaint case at Ex-Gratia Triage stage
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

  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can hard close a UKVI complaint case at Minor Misconduct Triage stage
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

  # HOCS-2870, HOCS-3096, HOCS-3022
  @COMPRegression @UKVIComplaints
  Scenario Outline: User can add and complete or cancel contributions to UKVI complaint cases as part of Service Triage stage
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
      | Complainant      | Cancel   |
      | Business         | Complete |
      | Business         | Cancel   |

  # HOCS-3103
  @COMPRegression @UKVIComplaints
  Scenario: User can tell if a contribution to a UKVI complaint case is overdue on the Triage Contributions page
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

  # HOCS-2979
  @COMPRegression @UKVIComplaints
  Scenario: User can select that a Letter of Authority is required for a UKVI complaint case
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service Triage" stage
    And I load and claim the current case
    And I accept the case at "Service" Triage stage
    And I accept the previous Claim Category selection
    And I accept the previous Case Details selection
    And I select that a Letter of Authority is required
    And I can mark that the LoA was received and enter the LoA date
    And I send the case to drafting
    And I load the current case
    And the read-only Case Details accordion should contain all case information entered during the "Service Triage" stage

  @Validation @UKVIComplaints
  Scenario Outline: User tests the validation for a UKVI complaint case at the Service Triage stage
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


#     UKVI COMPLAINTS STAGE 2

  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User completes the Service Triage stage for a UKVI stage 2 complaint case
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Service Triage" stage
    And I load and claim the current case
    And I accept the case at "Service" Triage stage
    And I accept the previous Claim Category selection
    And I accept the previous Case Details selection
    And I submit details on the Triage Capture Reason page
    When I send the case to drafting
    Then the case should be moved to the "Stage 2 Service Draft" stage
    And the summary should display the owning team as "Stage 2 CCT Response Team"
    And the read-only Case Details accordion should contain all case information entered during the "Stage 2 Service Triage" stage

  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can escalate a UKVI stage 2 complaint case at Service Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Service Triage" stage
    And I load and claim the current case
    And I accept the case at "Service" Triage stage
    And I accept the previous Claim Category selection
    And I accept the previous Case Details selection
    And I submit details on the Triage Capture Reason page
    And I escalate the case to WFM at Triage stage
    Then the case should be moved to the "Stage 2 Service Escalate" stage
    And the summary should display the owning team as "Stage 2 CCT Escalated"
    And an Escalation note should be visible in the timeline showing the submitted reason for the cases escalation
    And the read-only Case Details accordion should contain all case information entered during the "Stage 2 Service Triage" stage

  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can hard close a UKVI stage 2 complaint case at Service Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Service Triage" stage
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
    And the read-only Case Details accordion should contain all case information entered during the "Stage 2 Service Triage" stage

  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can transfer a Stage 2 UKVI complaint case back to CCH
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Service Triage" stage
    And I load and claim the current case
    And I select to Transfer the complaint
    And I enter a reason for "CCH" transfer and continue
    Then the case should be moved to the "Stage 2 CCH" stage
    And the summary should display the owning team as "Stage 2 CCH Returned Cases"
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case
    And the read-only Case Details accordion should contain all case information entered during the "Stage 2 Service Triage" stage


#     IEDET COMPLAINTS

  @ComplaintsWorkflow @IEDETAndSMCRegression @IEDETComplaints
  Scenario: User completes the Triage stage for an IEDET complaint case
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "Triage" stage
    And I load and claim the current case
    And I select the "Transferred to Third Party Supplier" action for an IEDET case at the Triage stage
    And I submit details on the Triage Capture Reason page
    Then the case should be moved to the "Draft" stage
    And the summary should display the owning team as "IE Detention"
    And the read-only Case Details accordion should contain all case information entered during the "Triage" stage

  @IEDETAndSMCRegression @IEDETComplaints
  Scenario: User can close an IEDET complaint case at the Triage stage
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "Triage" stage
    And I load and claim the current case
    And I select the "No Further Consideration" action for an IEDET case at the Triage stage
    Then the case should be closed


#     SMC COMPLAINTS

  @ComplaintsWorkflow @IEDETAndSMCRegression @SMCComplaints
  Scenario: User completes the Triage stage for an SMC complaint case
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

  # Expected failure. Defect HOCS-3980 raised.
  @ComplaintsWorkflow @IEDETAndSMCRegression @SMCComplaints
  Scenario: User can transfer a SMC complaint case to a UKVI complaint case at Triage stage
    Given I am logged into "CS" as user "SMC_USER"
    When I create a "SMC" case and move it to the "Triage" stage
    And I load and claim the current case
    And I select to Transfer the complaint
    And I enter a reason for "CCH" transfer and continue
    Then the case should be closed

  # Expected failure. Defect HOCS-3980 raised.
  @ComplaintsWorkflow @IEDETAndSMCRegression @SMCComplaints
  Scenario: User can transfer a SMC complaint case to an IEDET complaint case at Triage stage
    Given I am logged into "CS" as user "SMC_USER"
    When I create a "SMC" case and move it to the "Triage" stage
    And I load and claim the current case
    And I select to Transfer the complaint
    And I enter a reason for "IE Detention" transfer and continue
    Then the case should be closed


#     BF COMPLAINTS

  @ComplaintsWorkflow @BFRegression @BFComplaints
  Scenario: User completes the Case Triage stage for a BF complaint case
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF" case at the "Triage" stage
    And I accept the case at Triage stage
    And I enter information on the Triage Details page
    And I send the case to drafting
    Then the case should be moved to the "DRAFT" stage
    And the summary should display the owning team as "Border Force"
    And the read-only Case Details accordion should contain all case information entered during the "Case Triage" stage

  @ComplaintsWorkflow @BFRegression @BFComplaints
  Scenario: User is able to escalate a BF complaint case to workflow manager at the Case Triage stage
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF" case at the "Triage" stage
    And I accept the case at Triage stage
    And I enter information on the Triage Details page
    And I escalate the case to WFM at Triage stage
    Then the case should be moved to the "Escalated to WFM" stage
    And the summary should display the owning team as "Border Force"
    And an Escalation note should be visible in the timeline showing the submitted reason for the cases escalation
    And the read-only Case Details accordion should contain all case information entered during the "Case Triage" stage

  @BFRegression @BFComplaints
  Scenario: User can hard close a BF complaint case at the Triage stage
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF" case at the "Triage" stage
    And I accept the case at Triage stage
    And I enter information on the Triage Details page
    And I select to complete the case at Triage
    And I click the "Finish" button
    And I enter a reason for closing the case
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Case Triage" stage

  @BFRegression @BFComplaints
  Scenario Outline: User can add and complete or cancel contributions to BF complaint cases as part of Case Triage
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF" case at the "Triage" stage
    And I accept the case at Triage stage
    And I enter information on the Triage Details page
    And I add a "<contributionType>" contribution request
    And I "<action>" the contribution request
    Then the "<contributionType>" contribution request should be marked as "<action>"
    Examples:
    | contributionType  | action    |
    | Complainant       | Complete  |
    | Complainant       | Cancel    |
    | Business          | Complete  |
    | Business          | Cancel    |

  @BFRegression @BFComplaints
  Scenario: User is able to add Consolatory and Ex-Gratia payment offers to a BF case at Triage
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF" case at the "Triage" stage
    And I accept the case at Triage stage
    And I select "Yes" for has the complainant requested a payment
    And I enter "100.00" as the amount requested by the complainant
    And I select "Yes" for are we issuing an offer for a Consolatory payment
    And I enter "60.00" as the Consolatory payment offer sent to the complainant
    And I select "Yes" for are we issuing an offer for an Ex-Gratia payment
    And I enter "35.00" as the Ex-Gratia payment offer sent to the complainant
    And I save the changes
    Then the total payment offer sent to the complainant should be the sum of the payment offers previously entered


#     BF STAGE 2 COMPLAINTS

  @ComplaintsWorkflow @BFRegression @BFComplaints
  Scenario: User completes the Case Triage stage for a BF stage 2 complaint case
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF2" case at the "Triage" stage
    And I accept the case at Triage stage
    And I enter information on the Triage Details page
    And I send the case to drafting
    Then the case should be moved to the "DRAFT (STAGE 2)" stage
    And the summary should display the owning team as "Border Force Complaints (Stage 2)"
    And the read-only Case Details accordion should contain all case information entered during the "Case Triage (Stage 2)" stage

  @ComplaintsWorkflow @BFRegression @BFComplaints
  Scenario: User is able to escalate a BF stage 2 complaint case to workflow manager at the Case Triage stage
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF2" case at the "Triage" stage
    And I accept the case at Triage stage
    And I enter information on the Triage Details page
    And I escalate the case to WFM at Triage stage
    Then the case should be moved to the "Escalated to WFM (Stage 2)" stage
    And the summary should display the owning team as "Border Force Complaints (Stage 2)"
    And an Escalation note should be visible in the timeline showing the submitted reason for the cases escalation
    And the read-only Case Details accordion should contain all case information entered during the "Case Triage (Stage 2)" stage

  @ComplaintsWorkflow @BFRegression @BFComplaints
  Scenario: User can hard close a BF stage 2 complaint case at the Triage stage
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF2" case at the "Triage" stage
    And I accept the case at Triage stage
    And I enter information on the Triage Details page
    And I select to complete the case at Triage
    And I click the "Finish" button
    And I enter a reason for closing the case
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Case Triage (Stage 2)" stage

  @BFRegression @BFComplaints
  Scenario Outline: User can add and complete or cancel contributions to BF stage 2 complaint cases as part of Case Triage
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF2" case at the "Triage" stage
    And I accept the case at Triage stage
    And I enter information on the Triage Details page
    And I add a "<contributionType>" contribution request
    And I "<action>" the contribution request
    Then the "<contributionType>" contribution request should be marked as "<action>"
    Examples:
      | contributionType  | action    |
      | Complainant       | Complete  |
      | Complainant       | Cancel    |
      | Business          | Complete  |
      | Business          | Cancel    |

  @BFRegression @BFComplaints
  Scenario: User is able to add Consolatory and Ex-Gratia payment offers to a BF2 case at Triage
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF2" case at the "Triage" stage
    And I accept the case at Triage stage
    And I select "Yes" for has the complainant requested a payment
    And I enter "100.00" as the amount requested by the complainant
    And I select "Yes" for are we issuing an offer for a Consolatory payment
    And I enter "60.00" as the Consolatory payment offer sent to the complainant
    And I select "Yes" for are we issuing an offer for an Ex-Gratia payment
    And I enter "35.00" as the Ex-Gratia payment offer sent to the complainant
    And I save the changes
    Then the total payment offer sent to the complainant should be the sum of the payment offers previously entered


#     POGR COMPLAINTS

  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario: User is able to complete the Investigation stage for a POGR complaint case with HMPO as the business area
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR" case with "HMPO" as the Business Area at the "Investigation" stage
    And I "Accept" the case at the Investigation stage
    And I enter any required information at the Investigation stage
    And I complete the "All Information Collected - Respond" action at the Investigation stage
    Then the case should be moved to the "Draft" stage
    And the summary should display the owning team as "HMPO Complaints"
    And the read-only Case Details accordion should contain all case information entered during the "Investigation" stage

  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario: User is able to complete the Investigation stage for a POGR complaint case with GRO as the business area
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR" case with "GRO" as the Business Area at the "Investigation" stage
    And I "Accept" the case at the Investigation stage
    And I enter any required information at the Investigation stage
    And I complete the "All Information Collected - Respond" action at the Investigation stage
    Then the case should be moved to the "Draft" stage
    And the POGR case should be assigned to the correct investigating team
    And the read-only Case Details accordion should contain all case information entered during the "Investigation" stage

  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario Outline: User is able to close a POGR complaint case at the Investigation stage
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR" case with "<businessArea>" as the Business Area at the "Investigation" stage
    And I "Accept" the case at the Investigation stage
    And I complete the "No Response - Complete the Case" action at the Investigation stage
    And I select a Closure Reason
    And I enter a reason for closing the case
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Investigation" stage
    And a Case closure note should be visible in the timeline showing the submitted reason for closing the case
    Examples:
      | businessArea |
      | HMPO         |
      | GRO          |

  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario: User is able to transfer a POGR complaint case with HMPO business area to an external team at the Investigation stage
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR" case with "HMPO" as the Business Area at the "Investigation" stage
    And I "Reject" the case at the Investigation stage
    And I enter a transfer reason at the Investigation stage
    Then the case should be closed
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case
    And the read-only Case Details accordion should contain all case information entered during the "Investigation" stage

  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario: User is able to transfer a POGR complaint case with GRO business area to an external team at the Investigation stage
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR" case with "GRO" as the Business Area at the "Investigation" stage
    And I "Reject" the case at the Investigation stage
    And I enter a transfer reason at the Investigation stage
    And I select that the case is to be transferred to an "External" team
    Then the case should be closed
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case
    And the read-only Case Details accordion should contain all case information entered during the "Investigation" stage

  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario: User is able to transfer a POGR complaint case with GRO business area to an internal team at the Investigation stage
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR" case with "GRO" as the Business Area at the "Investigation" stage
    And I "Reject" the case at the Investigation stage
    And I enter a transfer reason at the Investigation stage
    And I select that the case is to be transferred to an "Internal" team
    And I select an investigating team
    Then the case should be at the "Investigation" stage
    And the POGR case should be assigned to the correct investigating team
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case
    And the read-only Case Details accordion should contain all case information entered during the "Investigation" stage

  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario Outline: User is able to escalate a POGR complaint case to workflow manager at the Investigation stage
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR" case with "<businessArea>" as the Business Area at the "Investigation" stage
    And I "Accept" the case at the Investigation stage
    And I escalate the case to WFM at Investigation stage
    Then the case should be moved to the "Escalated" stage
    And the POGR case should be assigned to the correct Escalation team
    And an Escalation note should be visible in the timeline showing the submitted reason for the cases escalation
    And the read-only Case Details accordion should contain all case information entered during the "Investigation" stage
    Examples:
      | businessArea |
      | HMPO         |
      | GRO          |

  @POGRRegression @POGRComplaints
  Scenario Outline: User can add and complete or cancel contributions for POGR complaint cases as part of the Investigation stage
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR" case at the "Investigation" stage
    And I "Accept" the case at the Investigation stage
    And I add a "<contributionType>" contribution request
    And I "<action>" the contribution request
    Then the "<contributionType>" contribution request should be marked as "<status>"
    Examples:
      | contributionType | action   | status    |
      | Complainant      | Complete | Complete  |
      | Complainant      | Cancel   | Cancelled |
      | Business         | Complete | Complete  |
      | Business         | Cancel   | Cancelled |


#     POGR (STAGE 2) COMPLAINTS


  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario: User is able to complete the Investigation stage for a POGR stage 2 complaint case with HMPO as the business area
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR2" case with "HMPO" as the Business Area at the "Investigation" stage
    And I "Accept" the case at the Investigation stage
    And I enter any required information at the Investigation stage
    And I complete the "All Information Collected - Respond" action at the Investigation stage
    Then the case should be moved to the "Draft" stage
    And the summary should display the owning team as "HMPO Complaints"
    And the read-only Case Details accordion should contain all case information entered during the "Investigation" stage

  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario: User is able to complete the Investigation stage for a POGR stage 2 complaint case with GRO as the business area
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR2" case with "GRO" as the Business Area at the "Investigation" stage
    And I "Accept" the case at the Investigation stage
    And I enter any required information at the Investigation stage
    And I complete the "All Information Collected - Respond" action at the Investigation stage
    Then the case should be moved to the "Draft" stage
    And the POGR case should be assigned to the correct investigating team
    And the read-only Case Details accordion should contain all case information entered during the "Investigation" stage

  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario Outline: User is able to close a POGR stage 2 complaint case at the Investigation stage
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR2" case with "<businessArea>" as the Business Area at the "Investigation" stage
    And I "Accept" the case at the Investigation stage
    And I complete the "No Response - Complete the Case" action at the Investigation stage
    And I select a Closure Reason
    And I enter a reason for closing the case
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Investigation" stage
    And a Case closure note should be visible in the timeline showing the submitted reason for closing the case
    Examples:
      | businessArea |
      | HMPO         |
      | GRO          |


# Currently broken due to HOCS-5547
  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario: User is able to transfer a POGR stage 2 complaint case with HMPO business area to an external team at the Investigation stage
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR2" case with "HMPO" as the Business Area at the "Investigation" stage
    And I "Reject" the case at the Investigation stage
    And I enter a transfer reason at the Investigation stage
    Then the case should be closed
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case
    And the read-only Case Details accordion should contain all case information entered during the "Investigation" stage

  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario: User is able to transfer a POGR stage 2 complaint case with GRO business area to an external team at the Investigation stage
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR2" case with "GRO" as the Business Area at the "Investigation" stage
    And I "Reject" the case at the Investigation stage
    And I enter a transfer reason at the Investigation stage
    And I select that the case is to be transferred to an "External" team
    Then the case should be closed
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case
    And the read-only Case Details accordion should contain all case information entered during the "Investigation" stage

  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario: User is able to transfer a POGR stage 2 complaint case with GRO business area to an internal team at the Investigation stage
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR2" case with "GRO" as the Business Area at the "Investigation" stage
    And I "Reject" the case at the Investigation stage
    And I enter a transfer reason at the Investigation stage
    And I select that the case is to be transferred to an "Internal" team
    And I select an investigating team
    Then the case should be at the "Investigation" stage
    And the POGR case should be assigned to the correct investigating team
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case
    And the read-only Case Details accordion should contain all case information entered during the "Investigation" stage

  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario Outline: User is able to escalate a POGR stage 2 complaint case to workflow manager at the Investigation stage
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR2" case with "<businessArea>" as the Business Area at the "Investigation" stage
    And I "Accept" the case at the Investigation stage
    And I escalate the case to WFM at Investigation stage
    Then the case should be moved to the "Escalated" stage
    And the POGR case should be assigned to the correct Escalation team
    And an Escalation note should be visible in the timeline showing the submitted reason for the cases escalation
    And the read-only Case Details accordion should contain all case information entered during the "Investigation" stage
    Examples:
      | businessArea |
      | HMPO         |
      | GRO          |

  @POGRRegression @POGRComplaints
  Scenario Outline: User can add and complete or cancel contributions for POGR stage 2 complaint cases as part of the Investigation stage
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR2" case at the "Investigation" stage
    And I "Accept" the case at the Investigation stage
    And I add a "<contributionType>" contribution request
    And I "<action>" the contribution request
    Then the "<contributionType>" contribution request should be marked as "<status>"
    Examples:
      | contributionType | action   | status    |
      | Complainant      | Complete | Complete  |
      | Complainant      | Cancel   | Cancelled |
      | Business         | Complete | Complete  |
      | Business         | Cancel   | Cancelled |