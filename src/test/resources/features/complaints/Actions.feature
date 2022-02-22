@ComplaintsActions @Complaints
Feature: Actions

  Background:
    Given I am logged into "CS" as user "BF_USER"

  # HOCS-4395, HOCS-4396, HOCS-5437
  @ComplaintsRegression
  Scenario: User is able to add and update a Registered Interest in a Border Force Complaints case
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
