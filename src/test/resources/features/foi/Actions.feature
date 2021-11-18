@FOIActions @FOI
Feature: Actions

  Background:
    Given I am logged into "CS" as user "FOI_USER"

  @FOIRegression
  Scenario: As a FOI User, I want to be able to apply an extension to a case, so that we have enough time to casework the request
    And I create a single "FOI" case with the correspondence received date set 20 workdays ago
    And I view the actions tab of the case
    When I select to extend the deadline of the FOI case
    And I select a type of extension
    And I select how many days to extend the deadline by and when to extend it from
    And I submit a reason for the extension
    Then I should see a confirmation message stating that the case has been extended
    And the deadline of the FOI case should be extended the correct number of days
    And a Case Extension note should be visible in the timeline showing the submitted reason for the extension
    And the teams workstack should display the new deadline date for the case

    #Scenario is waiting on completion of HOCS-4060 for correct behaviour of assertion step
  Scenario: As a FOI User, I want to be stopped from bringing the deadline forward, so that we always have enough time to casework the request
    And I create a single "FOI" case with the correspondence received date set as today
    And I view the actions tab of the case
    When I select to extend the deadline of the FOI case
    And I select a type of extension
    And I select to extend the case by "1" day from "Today"
    And I submit a reason for the extension
    Then I am unable to extend the case by this amount

  @FOIRegression
  Scenario: User is able to add and complete an Appeal for an FOI case
    And I create a single "FOI" case
    And I view the actions tab of the case
    When I select to add an appeal to the case
    And I submit details of the appeal type
    Then I should see a confirmation message stating that the appeal has been registered
    And the registered appeal should have the status "Pending" in the actions tab
    When I update and complete the registered appeal
    Then I should see a confirmation message stating that the appeal has been updated
    And the registered appeal should have the status "Complete" in the actions tab
      #This following step is broken until HOCS-4058 is completed (might need refactoring after)
#    And the information entered for the FOI appeal should be displayed

  @FOIRegression
  Scenario: User is able to add and update a Registered Interest in an FOI case
    And I create a single "FOI" case
    And I view the actions tab of the case
    When I select to record an interest in the case
    And I submit details of the interest the external party has in the case
    Then I should see a confirmation message stating that the external interest has been registered
    And the details of the interest should be visible in the actions tab
    When I update the registered interest
    Then I should see a confirmation message stating that the external interest has been updated
    And the updated details of the interest should be visible in the actions tab