@HomeSecretaryInterest @TO
Feature: Home Secretary Interest

  Background:
    Given I am logged into "CS" as user "TO_USER"

  @TOWorkflow @TORegression
  Scenario: As a Case Manager, I want a case with Home Secretary Interest to require additional approval, so that an incorrect reply is not dispatched
    And I get a Treat Official case at the "Draft" stage that has Home Secretary Interest
    When I add an "Initial Draft" type document to the case
    And I send the Treat Official case to Dispatch
    Then the case should be moved to the "Home Secretary Interest" stage

  @TOWorkflow @TORegression
  Scenario: As a Case Manager, I want a case with Home Secretary Interest to require additional approval, so that an incorrect reply is not dispatched
    And I get a Treat Official case at the "QA" stage that has Home Secretary Interest
    And I complete the "QA" stage
    Then the case should be moved to the "Home Secretary Interest" stage