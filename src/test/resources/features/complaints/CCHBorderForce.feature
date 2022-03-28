@CCH @Complaints @BFComplaints
Feature: CCH

  @ComplaintsRegression2 @HOCS-4221
  Scenario: User can transfer a BF complaints case to CCH
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case and move it to the "CCH" stage
    Then the case should be closed
    And I logout of the application
    And I am logged into "CS" as user "COMP_USER"
    And I click to view the case in the "Complaint Registration" workstack
    And the transferred case appears in "COMP" registration workstack
    And I click the link for the transferred case in the workstack
    And the summary should contain the Old Case Reference