Feature: Send Copy to Number 10

  Background:
    Given I log in as the designated user
    And I am at the "QA" stage

  @HOCS-471
  Scenario: User needs to send a copy of the case to Number 10
    When I "dispatch" the case
    Then I am taken to the "home" page
    And the case is completed
