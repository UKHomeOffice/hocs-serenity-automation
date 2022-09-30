@ComplaintsSend @Complaints
Feature: Complaints Dispatch & Send

#     UKVI COMPLAINTS

  # HOCS-2722, HOCS-3076
  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can complete service send stage for a UKVI complaint case
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service Send" stage
    And I load the current case
    And I upload a copy of the Final Response
    And I select a Case Outcome
    And I submit the Response details
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Service Send" stage

  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can complete Ex-Gratia send stage for a UKVI complaint case
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Ex-Gratia Send" stage
    And I load the current case
    And I upload a copy of the Final Response
    And I select a Case Outcome
    And I submit the Response details
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Ex-Gratia Send" stage

  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can complete Minor Misconduct send stage for a UKVI complaint case
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Minor Misconduct Send" stage
    And I load the current case
    And I upload a copy of the Final Response
    And I select a Case Outcome
    And I submit the Response details
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Minor Misconduct Send" stage

  @Validation @UKVIComplaints
  Scenario Outline: User tests the validation for a UKVI complaint case at the Service Send stage
    When I create a "COMP" case and move it to the "Service Send" stage
    And I load and claim the current case
    And I trigger the "<errorType>" error message at the "Service Send" stage
    Then the "<errorType>" error message is displayed at the "Service Send" stage
    Examples:
      | errorType                 |
      | CASE OUTCOME REQUIRED     |
      | RESPONSE CHANNEL REQUIRED |
      | DATE OF RESPONSE REQUIRED |


#     UKVI COMPLAINTS STAGE 2

  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can complete service send stage for a UKVI stage 2 complaint case
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Service Send" stage
    And I load the current case
    And I upload a copy of the Final Response
    And I select a Case Outcome
    And I submit the Response details
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Stage 2 Service Send" stage


#     IEDET COMPLAINTS

  @ComplaintsWorkflow @IEDETRegression @IEDETComplaints
  Scenario: User can complete the Send stage for an IEDET complaint case
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "Send" stage
    And I load the current case
    And I upload a copy of the Final Response
    And I select a Case Outcome
    And I submit the Response details
    Then the case should be closed


#     BF COMPLAINTS

  @ComplaintsWorkflow @BFRegression @BFComplaints
  Scenario: User can complete the Send stage for a BF complaint case
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF" case at the "Send" stage
    And I can see the selected Reasons for Complaint
    And I select a Case Outcome for each Reason for Complaint
    And I submit the Response details
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Send draft response" stage


#     BF STAGE 2 COMPLAINTS

  @ComplaintsWorkflow @BFRegression @BFComplaints
  Scenario: User can complete the Send stage for a BF stage 2 complaint case
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF2" case at the "Send" stage
    And I can see the selected Reasons for Complaint
    And I select a Case Outcome for each Reason for Complaint
    And I submit the Response details
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Send (Stage 2)" stage
    And the summary should display "Service" for "Complaint Type"


#     POGR COMPLAINTS

  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario: User can complete the Dispatch stage for a HMPO POGR complaint case
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR" case with "HMPO" as the Business Area at the "Dispatch" stage
    And I add a "Final Response" type document to the case
    And I select a Dispatch Outcome
    And I select if a refund is required
    And I enter details of any Gratis offered
    And I submit the Response details
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Dispatch" stage

  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario: User can complete the Dispatch stage for a GRO POGR complaint case
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR" case with "GRO" as the Business Area at the "Dispatch" stage
    And I add a "Final Response" type document to the case
    And I select a Dispatch Outcome
    And I submit the Response details
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Dispatch" stage


#     POGR (STAGE 2) COMPLAINTS

  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario: User can complete the Dispatch stage for a HMPO POGR stage 2 complaint case
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR2" case with "HMPO" as the Business Area at the "Dispatch" stage
    And I add a "Final Response" type document to the case
    And I select a Dispatch Outcome
    And I select if a refund is required
    And I enter details of any Gratis offered
    And I submit the Response details
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Dispatch" stage

  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario: User can complete the Dispatch stage for a GRO POGR stage 2 complaint case
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR2" case with "GRO" as the Business Area at the "Dispatch" stage
    And I add a "Final Response" type document to the case
    And I select a Dispatch Outcome
    And I submit the Response details
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Dispatch" stage


#  SMC workflow cancelled. Steps and code might be useful for future work implementing PSU specific sub-workflow into other complaints workflows

#     SMC COMPLAINTS

  @ComplaintsPWorkflow @SMCComplaints
  Scenario: User can complete the Send stage for an SMC complaint case
    Given I am logged into "CS" as user "SMC_USER"
    When I get a "SMC" case at the "Send" stage
    And I add a "Final Response" type document to the case
    And I select a Case Outcome
    And I submit the Response details
    Then the case should be closed