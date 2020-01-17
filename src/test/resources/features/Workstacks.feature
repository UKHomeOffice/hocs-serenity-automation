Feature: Team members can allocate work

  Background:
    Given I am user "EAMON"

  Scenario: User can allocate a case to themselves in a team workstack
    And I create a new case and view it in the Performance and Process team workstack
    When I select the check box against the case and allocate it to myself
    Then the case should be allocated to me

  Scenario: User can allocate a case to another user in a team workstack
    And I enter the Performance and process team workstack
    And I select a currently unallocated case
    When I select the user "eamon.droko"

  Scenario: User can unallocate a case in a team workstack

  Scenario: Only cases assigned to a user appear in that users My Cases workstack

  Scenario: User can unallocate a case in their workstack

  Scenario: Workstack should contain the Case Reference, Current Stage, Owner, Owning Team and Deadline when searching by
  Case Type
    And I navigate to the "search" page
    Then the search results should contain the expected information


  @Filtering @DCUMIN
  Scenario: User is able to filter cases in the workstack using case type cards
    When I click the "MIN" case type filter card
    Then the cases should be filtered by the "MIN" Case Reference

  Scenario: User is able to filter cases in the workstack using stage cards

  @Filtering @DCUMIN
  Scenario: Cases are filtered by Case Reference in Team Workstacks
    When I navigate to the "PERFORMANCE AND PROCESS TEAM" team page
    And I enter the Case Reference type "MIN" into the filter
    Then the cases should be filtered by the "MIN" Case Reference