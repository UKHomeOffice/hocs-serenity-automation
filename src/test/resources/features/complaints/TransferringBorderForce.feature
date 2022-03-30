@CCH @PSU @Complaints @BFComplaints
Feature: Transferring Border Force cases to CCH and PSU

  @ComplaintsRegression2 @HOCS-4050
  Scenario: User can transfer a BF complaints case to CCH
    Given I am logged into "CS" as user "BF_USER"
    And I create a "BF" case and move it to the "Triage" stage
    And I load and claim the current case
    When I do not accept the case at Triage
    And I enter a reason for "CCH" transfer and continue
    Then the case should be closed
    And the summary should display "CCH" for "Transfer to"
    And I switch to user "COMP_USER"
    And I click to view the case in the "Complaint Registration" workstack
    And the transferred case appears in "COMP" registration workstack
    And I click the link for the transferred case in the workstack
    And the summary should contain the old case reference


  @ComplaintsRegression2 @HOCS-4221
  Scenario: User can transfer a BF complaints case to PSU
    Given I am logged into "CS" as user "BF_USER"
    And I create a "BF" case and move it to the "Triage" stage
    And I load and claim the current case
    When I do not accept the case at Triage
    And I enter a reason for "PSU" transfer and continue
    Then the case should be closed
    And the summary should display "PSU" for "Transfer to"
    And I switch to user "SMC_USER"
    And I click to view the case in the "SMC Registration" workstack
    And the transferred case appears in "SMC" registration workstack
    And I click the link for the transferred case in the workstack
    And the summary should contain the old case reference