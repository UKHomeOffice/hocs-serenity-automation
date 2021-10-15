@Superuser @COMP
Feature: Superuser

  @COMPRegression
  Scenario: A Complaints Admin User can complete the COMP workflow
    Given I log in to "CS" as user "COMP_SUPERUSER"
    When I create a "COMP" case and move it to "Service Case Closed"
    Then the case should be closed

  @COMPRegression
  Scenario: A Complaints Admin User can complete a stage whilst case is owned by another user
    Given I log in to "CS" as user "COMP_USER"
    And I create a single "COMP" case
    When I allocate the case to myself via the successful case creation screen
    And I switch to user "COMP_SUPERUSER"
    And I load the current case
    And I complete the "Registration (to Service Triage)" stage
    Then the case should be moved to the "Service Triage" stage
    And I logout of the application