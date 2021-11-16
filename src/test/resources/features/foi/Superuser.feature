@Superuser @FOI
Feature: Superuser

  @ComplaintsRegression
  Scenario: A FOI Admin User can complete the FOI workflow
    Given I log in to "CS" as user "FOI_SUPERUSER"
    When I create a "FOI" case and move it to "Soft Close"
    Then the case should be at the "Soft Close" stage

  @ComplaintsRegression
  Scenario: A FOI Admin User can complete a stage whilst case is owned by another user
    Given I log in to "CS" as user "FOI_USER"
    And I create a single "FOI" case
    And I switch to user "FOI_SUPERUSER"
    And I load the current case
    And I complete the "Case Creation" stage
    Then the case should be moved to the "Allocation" stage
    And I logout of the application