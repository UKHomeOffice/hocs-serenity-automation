@Registration  @COMP
Feature: Registration

  Background:
    Given I am logged into "DECS" as user "COMP_USER"
    And I create a single "COMP" case
    And I allocate the case to myself via the successful case creation screen

  Scenario: User can complete the Registration stage for a Service complaint
    And I add a "" correspondent to the case
