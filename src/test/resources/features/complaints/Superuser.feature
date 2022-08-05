@Superuser @Complaints
Feature: Superuser


  #     UKVI STAGE 1 COMPLAINTS

  @ComplaintsRegression1 @UKVIComplaints
  Scenario: A UKVI Complaints Admin User can complete the UKVI stage 1 complaint workflow
    Given I log in to "CS" as user "COMP_SUPERUSER"
    When I create a "COMP" case and move it to "COMPLAINT CLOSED"
    Then the case should be closed

  @ComplaintsRegression1 @UKVIComplaints
  Scenario: A UKVI Complaints Admin User can complete a stage of the UKVI stage 1 workflow whilst the case is owned by another user
    Given I log in to "CS" as user "COMP_USER"
    And I create a single "COMP" case
    When I allocate the case to myself via the successful case creation screen
    And I switch to user "COMP_SUPERUSER"
    And I load the current case
    And I move the case from "Registration" stage to "Service Triage" stage
    Then the case should be moved to the "Service Triage" stage
    And I logout of the application


#     BF STAGE 1 COMPLAINTS

  @ComplaintsRegression2 @BFComplaints
  Scenario: A BF Complaints Admin User can complete the BF stage 1 complaint workflow
    Given I log in to "CS" as user "BF_SUPERUSER"
    When I create a "BF" case and move it to "CASE CLOSED"
    Then the case should be closed

  @ComplaintsRegression2 @BFComplaints
  Scenario: A BF Complaints Admin User can complete a stage of the BF stage 1 workflow whilst the case is owned by another user
    Given I log in to "CS" as user "BF_USER"
    And I create a single "BF" case
    When I allocate the case to myself via the successful case creation screen
    And I switch to user "BF_SUPERUSER"
    And I load the current case
    And I move the case from "Registration" stage to "Triage" stage
    Then the case should be moved to the "Case Triage" stage
    And I logout of the application


#     BF STAGE 2 COMPLAINTS

  @ComplaintsRegression2 @BFComplaints
  Scenario: A BF Complaints Admin User can complete the BF stage 2 complaint workflow
    Given I log in to "CS" as user "BF_SUPERUSER"
    When I create a "BF2" case and move it to "CASE CLOSED"
    Then the case should be closed

  @ComplaintsRegression2 @BFComplaints
  Scenario: A BF Complaints Admin User can complete a stage of the BF stage 2 workflow whilst the case is owned by another user
    Given I log in to "CS" as user "BF_USER"
    And I create a single "BF2" case
    When I allocate the case to myself via the successful case creation screen
    And I switch to user "BF_SUPERUSER"
    And I load the current case
    And I move the case from "Registration" stage to "Triage" stage
    Then the case should be moved to the "Case Triage (Stage 2)" stage
    And I logout of the application