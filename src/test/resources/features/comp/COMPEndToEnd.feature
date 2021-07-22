@COMPEndToEnd @COMP @COMPWorkflow
Feature: COMP End To End

  Background:
    Given I am logged into "DECS" as user "COMP_USER"

  Scenario: User moves a COMP case to the Registration stage
    When I create a "COMP" case and move it to the "Registration" stage
    Then the case should be moved to the "Registration" stage

  Scenario: User moves a COMP case to the Service Triage stage
    When I create a "COMP" case and move it to the "Service Triage" stage
    Then the case should be moved to the "Service Triage" stage

  Scenario: User moves a COMP case to CCH
    When I create a "COMP" case and move it to "CCH"
    Then the case should be moved to "CCH"

  Scenario: User moves a COMP case to Service Escalated
    When I create a "COMP" case and move it to "Service Escalated"
    Then the case should be moved to "Service Escalated"

  Scenario: User moves a COMP case to the Service Draft stage
    When I create a "COMP" case and move it to the "Service Draft" stage
    Then the case should be moved to the "Service Draft" stage

  Scenario: User moves a COMP case to the Service QA stage
    When I create a "COMP" case and move it to the "Service QA" stage
    Then the case should be moved to the "Service QA" stage

  Scenario: User moves a COMP case to the Service Send stage
    When I create a "COMP" case and move it to the "Service Send" stage
    Then the case should be moved to the "Service Send" stage

  Scenario: User moves a COMP case to the Complaint Closed stage
    When I create a "COMP" case and move it to the "Complaint Closed" stage
    Then the case should be moved to the "Complaint Closed" stage

  @COMPRegression @Smoketests
  Scenario: User can complete and close a COMP case
    When I create a "COMP" case and move it to "Case Closed"
    Then the case should be closed