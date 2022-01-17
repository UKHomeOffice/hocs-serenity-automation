@Workstacks @FOI
Feature: Workstacks

  Background:
    Given I am logged into "CS" as user "FOI_USER"

  @FOIRegression
  Scenario: FOI User sees the required information when viewing a workstack
    When I enter a "FOI Team" workstack
    Then the "FOI Team" workstack should contain only the expected columns

  @FOIRegression
  Scenario: User is able to see a red highlighted deadline on an FOI case that is past its deadline date
    When I create a single "FOI" case with the correspondence received date set 21 workdays ago
    And I click to view the case in the "FOI Creation" workstack
    Then the case deadline should be highlighted "red"
