@COMPEndToEnd @COMP @COMPWorkflow
Feature: COMP End To End

  Background:
    Given I am logged into "CS" as user "COMP_USER"

  Scenario: User moves a COMP case to the Registration stage
    When I create a "COMP" case and move it to the "Registration" stage
    Then the case should be moved to the "Registration" stage

  Scenario: User moves a COMP case to the Service Triage stage
    When I create a "COMP" case and move it to the "Service Triage" stage
    Then the case should be moved to the "Service Triage" stage

  Scenario: User moves a COMP case to the Ex-Gratia Triage stage
    When I create a "COMP" case and move it to the "Ex-Gratia Triage" stage
    Then the case should be moved to the "Ex-Gratia Triage" stage

  Scenario: User moves a COMP case to CCH
    When I create a "COMP" case and move it to "CCH"
    Then the case should be moved to "CCH"

  Scenario: User moves a COMP case to Service Escalated
    When I create a "COMP" case and move it to "Service Escalated"
    Then the case should be moved to "Service Escalated"

  Scenario: User moves a COMP case to the Service Draft stage
    When I create a "COMP" case and move it to the "Service Draft" stage
    Then the case should be moved to the "Service Draft" stage

  Scenario: User moves a COMP case to the Ex-Gratia Response Draft stage
    When I create a "COMP" case and move it to the "Ex-Gratia Response Draft" stage
    Then the case should be moved to the "Ex-Gratia Response Draft" stage

  Scenario: User moves a COMP case to the Service QA stage
    When I create a "COMP" case and move it to the "Service QA" stage
    Then the case should be moved to the "Service QA" stage

  Scenario: User moves a COMP case to the Ex-Gratia QA stage
    When I create a "COMP" case and move it to the "Ex-Gratia QA" stage
    Then the case should be moved to the "Ex-Gratia QA" stage

  Scenario: User moves a COMP case to the Service Send stage
    When I create a "COMP" case and move it to the "Service Send" stage
    Then the case should be moved to the "Service Send" stage

  Scenario: User moves a COMP case to the Ex-Gratia Send stage
    When I create a "COMP" case and move it to the "Ex-Gratia Send" stage
    Then the case should be moved to the "Ex-Gratia Send" stage

  Scenario: User moves a COMP case from Service Send to the Complaint Closed stage
    When I create a "COMP" case and move it to the "Complaint Closed (from Service Send)" stage
    Then the case should be moved to the "Complaint Closed" stage

  Scenario: User moves a COMP case from Ex-Gratia Send to the Complaint Closed stage
    When I create a "COMP" case and move it to the "Complaint Closed (from Ex-Gratia Send)" stage
    Then the case should be moved to the "Complaint Closed" stage

  @COMPRegression @Smoketests
  Scenario: User can complete and close a Service COMP case
    When I create a "COMP" case and move it to "Service Case Closed"
    Then the case should be closed

  @COMPRegression @Smoketests
  Scenario: User can complete and close a Ex-Gratia COMP case
    When I create a "COMP" case and move it to "Ex-Gratia Case Closed"
    Then the case should be closed