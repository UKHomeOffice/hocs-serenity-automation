@SoftClose @FOI @FOIWorkflow
Feature: Soft Close

  Background:
    Given I am logged into "CS" as user "FOI_USER"
    When I create a "FOI" case and move it to the "Soft Close" stage
    And I load and claim the current case

  Scenario: User is able to reopen a case at Soft Close
    And I click the "Reopen" button
    Then the case should be moved to the "Case Creation" stage
