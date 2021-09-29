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

  Scenario: User moves a COMP case to the Minor Misconduct Triage stage
    When I create a "COMP" case and move it to the "Minor Misconduct Triage" stage
    Then the case should be moved to the "Minor Misconduct Triage" stage

  Scenario: User moves a Service COMP case to CCH
    When I create a "COMP" case and move it to "CCH (From Service Triage)"
    Then the case should be moved to "CCH"

  Scenario: User moves a Ex-Gratia COMP case to CCH
    When I create a "COMP" case and move it to "CCH (From Ex-Gratia Triage)"
    Then the case should be moved to "CCH"

  Scenario: User moves a Minor Misconduct COMP case to CCH
    When I create a "COMP" case and move it to "CCH (From Minor Misconduct Triage)"
    Then the case should be moved to "CCH"

  Scenario: User moves a COMP case to Service Escalated
    When I create a "COMP" case and move it to "Service Escalated"
    Then the case should be moved to "Service Escalated"

  Scenario: User moves a COMP case to Ex-Gratia Escalate
    When I create a "COMP" case and move it to "Ex-Gratia Escalate"
    Then the case should be moved to "Ex-Gratia Escalate"

  Scenario: User moves a COMP case to Minor Misconduct Escalate
    When I create a "COMP" case and move it to "Minor Misconduct Escalate"
    Then the case should be moved to "Minor Misconduct Escalate"

  Scenario: User moves a COMP case to the Service Draft stage
    When I create a "COMP" case and move it to the "Service Draft" stage
    Then the case should be moved to the "Service Draft" stage

  Scenario: User moves a COMP case to the Ex-Gratia Response Draft stage
    When I create a "COMP" case and move it to the "Ex-Gratia Response Draft" stage
    Then the case should be moved to the "Ex-Gratia Response Draft" stage

  Scenario: User moves a COMP case to the Minor Misconduct Response Draft stage
    When I create a "COMP" case and move it to the "Minor Misconduct Response Draft" stage
    Then the case should be moved to the "Minor Misconduct Response Draft" stage

  Scenario: User moves a COMP case to the Service QA stage
    When I create a "COMP" case and move it to the "Service QA" stage
    Then the case should be moved to the "Service QA" stage

  Scenario: User moves a COMP case to the Ex-Gratia QA stage
    When I create a "COMP" case and move it to the "Ex-Gratia QA" stage
    Then the case should be moved to the "Ex-Gratia QA" stage

  Scenario: User moves a COMP case to the Minor Misconduct QA stage
    When I create a "COMP" case and move it to the "Minor Misconduct QA" stage
    Then the case should be moved to the "Minor Misconduct QA" stage

  Scenario: User moves a COMP case to the Service Send stage
    When I create a "COMP" case and move it to the "Service Send" stage
    Then the case should be moved to the "Service Send" stage

  Scenario: User moves a COMP case to the Ex-Gratia Send stage
    When I create a "COMP" case and move it to the "Ex-Gratia Send" stage
    Then the case should be moved to the "Ex-Gratia Send" stage

  Scenario: User moves a COMP case to the Minor Misconduct Send stage
    When I create a "COMP" case and move it to the "Minor Misconduct Send" stage
    Then the case should be moved to the "Minor Misconduct Send" stage

  Scenario: User moves a COMP case from Service Send to the Complaint Closed stage
    When I create a "COMP" case and move it to the "Complaint Closed (from Service Send)" stage
    Then the case should be moved to the "Complaint Closed" stage

  Scenario: User moves a COMP case from Ex-Gratia Send to the Complaint Closed stage
    When I create a "COMP" case and move it to the "Complaint Closed (from Ex-Gratia Send)" stage
    Then the case should be moved to the "Complaint Closed" stage

  Scenario: User moves a COMP case from Minor Misconduct Send to the Complaint Closed stage
    When I create a "COMP" case and move it to the "Complaint Closed (from Minor Misconduct Send)" stage
    Then the case should be moved to the "Complaint Closed" stage

  @COMPRegression @Smoketests
  Scenario: User can complete and close a Service COMP case
    When I create a "COMP" case and move it to "Service Case Closed"
    Then the case should be closed

  @COMPRegression @Smoketests
  Scenario: User can complete and close a Ex-Gratia COMP case
    When I create a "COMP" case and move it to "Ex-Gratia Case Closed"
    Then the case should be closed

  @COMPRegression @Smoketests
  Scenario: User can complete and close a Minor Misconduct COMP case
    When I create a "COMP" case and move it to "Minor Misconduct Case Closed"
    Then the case should be closed

  Scenario: User moves a COMP case to the Stage 2 Registration stage
    When I create a "COMP2" case and move it to the "Stage 2 Registration" stage
    Then the case should be moved to the "Stage 2 Registration" stage

  Scenario Outline: User moves a COMP case to the Stage 2 Triage stage
    When I create a "COMP2" case and move it to the "Stage 2 <complaintType> Triage" stage
    Then the case should be moved to the "Stage 2 <complaintType> Triage" stage
    Examples:
    | complaintType |
    | Service       |
    | Ex-Gratia     |
    | MM            |

  Scenario Outline: User moves a COMP case to the Stage 2 Draft stage
    When I create a "COMP2" case and move it to the "Stage 2 <complaintType> Draft" stage
    Then the case should be moved to the "Stage 2 <complaintType> Draft" stage
    Examples:
      | complaintType      |
      | Service            |
      | Ex-Gratia Response |
      | MM Response        |

  Scenario Outline: User moves a COMP case to the Stage 2 QA stage
    When I create a "COMP2" case and move it to the "Stage 2 <complaintType> QA" stage
    Then the case should be moved to the "Stage 2 <complaintType> QA" stage
    Examples:
      | complaintType  |
      | Service        |
      | Ex-Gratia      |
      | MM             |

  Scenario Outline: User moves a COMP case to the Stage 2 Send stage
    When I create a "COMP2" case and move it to the "Stage 2 <complaintType> Send" stage
    Then the case should be moved to the "Stage 2 <complaintType> Send" stage
    Examples:
      | complaintType  |
      | Service        |
      | Ex-Gratia      |
      | MM             |

  Scenario Outline: User moves a COMP case to the Stage 2 Complaint Closed stage
    When I create a "COMP2" case and move it to the "Stage 2 Complaint Closed (From Stage 2 <complaintType> Send)" stage
    Then the case should be moved to the "Stage 2 Complaint Closed" stage
    Examples:
      | complaintType  |
      | Ex-Gratia      |
      | MM             |

  @COMPRegression
  Scenario: User is able to close a COMP2 case
    When I create a "COMP2" case and move it to the "Stage 2 Complaint Closed (From Stage 2 Service Send)" stage
    Then the case should be closed