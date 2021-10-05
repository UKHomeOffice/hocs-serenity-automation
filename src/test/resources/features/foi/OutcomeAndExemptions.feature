@FOIOutcomesAndExemptions @FOI @FOIWorkflow
Feature: FOIOutcomesAndExemptions

  Background:
    Given I am logged into "CS" as user "FOI_USER"
    When I create a "FOI" case and move it to the "Dispatch" stage

  @FOIRegression
  Scenario: User is able to navigate to Exemptions page
    And I select "FOI" as the case type
    And I select "Email" as the response
    And I select non dispatch option as outcome of the case
    And I click the "Continue" button
    Then the "Which exemptions were applied?" page should be displayed
    And I select an Exemption
    And I click the "Continue" button
    And I select "Yes" to do you want to dispatch the case
    And I click the "Complete Dispatch" button
    Then the case should be moved to the "Soft Close" stage

  @FOIRegression
  Scenario: User is able to navigate to Exceptions page
    And I select "EIR" as the case type
    And I select "Email" as the response
    And I select non dispatch option as outcome of the case
    And I click the "Continue" button
    Then the "Which exceptions were applied?" page should be displayed
    And I select an Exception
    And I click the "Continue" button
    And I select "Yes" to do you want to dispatch the case
    And I click the "Complete Dispatch" button
    Then the case should be moved to the "Soft Close" stage

  @FOIRegression
  Scenario: User is able to navigate to Exemptions and Exceptions page
    And I select "FOI & EIR" as the case type
    And I select "Email" as the response
    And I select non dispatch option as outcome of the case
    And I click the "Continue" button
    Then the "Which exemptions and exceptions were applied?" page should be displayed
    And I select an Exception and an Exemption
    And I click the "Continue" button
    And I select "Yes" to do you want to dispatch the case
    And I click the "Complete Dispatch" button
    Then the case should be moved to the "Soft Close" stage