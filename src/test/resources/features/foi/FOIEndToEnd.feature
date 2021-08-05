@FOIEndToEnd @FOI @FOIWorkflow
Feature: FOI End To End

  Background:
    Given I am logged into "CS" as user "FOI_USER"

  Scenario: User moves an FOI case to the Case Creation stage
    When I create a "FOI" case and move it to the "Case Creation" stage
    Then the case should be moved to the "Case Creation" stage

  Scenario: User moves an FOI case to the KIMU Allocation stage
    When I create a "FOI" case and move it to the "KIMU Allocation" stage
    Then the case should be moved to the "KIMU Allocation" stage

  Scenario: User moves an FOI case to the Case Creation stage
    When I create a "FOI" case and move it to the "Case Creation" stage
    Then the case should be moved to the "Case Creation" stage

  Scenario: User moves an FOI case to the Acceptance stage
    When I create a "FOI" case and move it to the "Acceptance" stage
    Then the case should be moved to the "Acceptance" stage

  Scenario: User moves an FOI case to the Consider and Draft stage
    When I create a "FOI" case and move it to the "Consider and Draft" stage
    Then the case should be moved to the "Consider and Draft" stage

  Scenario: User moves an FOI case to the Approval stage
    When I create a "FOI" case and move it to the "Approval" stage
    Then the case should be moved to the "Approval" stage

  Scenario: User moves an FOI case to the Dispatch stage
    When I create a "FOI" case and move it to the "Dispatch" stage
    Then the case should be moved to the "Dispatch" stage

  @FOIRegression
  Scenario: User moves a case to Soft Close
    When I create a "FOI" case and move it to the "Soft Close" stage
    Then the case should be moved to the "Soft Close" stage