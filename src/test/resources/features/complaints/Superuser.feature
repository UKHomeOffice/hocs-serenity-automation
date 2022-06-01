@Superuser @Complaints
Feature: Superuser

  @ComplaintsRegression1 @UKVIComplaints
  Scenario: A Complaints Admin User can complete the UKVI stage 1 complaint workflow
    Given I log in to "CS" as user "COMP_SUPERUSER"
    When I create a "COMP" case and move it to "COMPLAINT CLOSED"
    Then the case should be closed

  @ComplaintsRegression1 @UKVIComplaints
  Scenario: A Complaints Admin User can complete a stage of the UKVI stage 1 workflow whilst the case is owned by another user
    Given I log in to "CS" as user "COMP_USER"
    And I create a single "COMP" case
    When I allocate the case to myself via the successful case creation screen
    And I switch to user "COMP_SUPERUSER"
    And I load the current case
    And I move the case from "Registration" stage to "Service Triage" stage
    Then the case should be moved to the "Service Triage" stage
    And I logout of the application