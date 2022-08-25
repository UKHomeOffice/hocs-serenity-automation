@ComplaintsActions @Complaints
Feature: Actions

  # HOCS-4395, HOCS-4396, HOCS-5437
  @BFRegression @BFComplaints
  Scenario: User is able to add and update a Registered Interest in a Border Force Complaints case
    Given I am logged into "CS" as user "BF_USER"
    And I create a single "BF" case
    And I view the actions tab of the case
    When I select to record an interest in the case
    And I submit details of the interest the external party has in the case
    Then I should see a confirmation message stating that the external interest has been registered
    And the details of the interest should be visible in the actions tab
    And an Interest Created log should be visible in the timeline for the interested party
    When I update the registered interest
    Then I should see a confirmation message stating that the external interest has been updated
    And the updated details of the interest should be visible in the actions tab
    And an Interest Updated log should be visible in the timeline for the interested party

  @BFRegression @BFComplaints
  Scenario: User is able to add and update a Registered Interest in a Border Force Stage 2 Complaints case
    Given I am logged into "CS" as user "BF_USER"
    And I create a single "BF2" case
    And I view the actions tab of the case
    When I select to record an interest in the case
    And I submit details of the interest the external party has in the case
    Then I should see a confirmation message stating that the external interest has been registered
    And the details of the interest should be visible in the actions tab
    And an Interest Created log should be visible in the timeline for the interested party
    When I update the registered interest
    Then I should see a confirmation message stating that the external interest has been updated
    And the updated details of the interest should be visible in the actions tab
    And an Interest Updated log should be visible in the timeline for the interested party

  @IEDETAndSMCRegression @SMCComplaints
  Scenario: As a SMC caseworker, I want to be able to suspend a case, so that the case and its SLA reflect its current status
    Given I am logged into "CS" as user "SMC_USER"
    And I get a new "SMC" case
    And I select the actions tab
    When I suspend the case
    Then I should see a confirmation message stating that the case has been suspended
    And the details of the current suspension should be visible in the actions tab
    And a Case Suspension Applied log should be visible in the timeline
    And the deadline of the case should be replaced with the word "Suspended" in the Summary tab
    And the deadline of the case should be replaced with the word "Suspended" in the "SMC Registration" workstack
    And the deadline of the case should be replaced with the word "Suspended" in the My Cases workstack

  @IEDETAndSMCRegression @SMCComplaints
  Scenario: As a SMC caseworker, I want to be able to remove the suspension on a case, so that the case reflect its current status
    Given I am logged into "CS" as user "SMC_USER"
    And I get a new "SMC" case
    And I select the actions tab
    And I suspend the case
    And I return to the case from the confirmation screen
    And I remove the suspension from the case
    Then I should see a confirmation message stating that the suspension has been removed from the case
    And the details of the previous suspension should be visible in the actions tab
    And the deadline of the case should be replaced with "N/A" in the Summary tab
    And the deadline of the case should be replaced with "N/A" in the "SMC Registration" workstack
    And the deadline of the case should be replaced with "N/A" in the My Cases workstack


