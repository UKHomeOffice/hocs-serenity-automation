@COMPEndToEnd @COMP @COMPWorkflow
Feature: COMP End To End

  Background:
    Given I am logged into "CS" as user "COMP_USER"

  Scenario Outline: User moves a complaints case to the Registration stage
    When I create a "<caseType>" case and move it to the "Registration" stage
    Then the case should be moved to the "Registration" stage
    Examples:
    | caseType  |
    | COMP      |
    | IEDET     |


  Scenario Outline: User moves a COMP case to the Triage stage
    When I create a "COMP" case and move it to the "<complaintType> Triage" stage
    Then the case should be moved to the "<complaintType> Triage" stage
    Examples:
      | complaintType     |
      | Service           |
      | Ex-Gratia         |
      | Minor Misconduct  |

  Scenario: User moves an IEDET case to the Triage stage
    When I create a "IEDET" case and move it to the "Triage" stage
    Then the case should be moved to the "Triage" stage

  Scenario Outline: User moves a COMP case to CCH
    When I create a "COMP" case and move it to "CCH (From <complaintType> Triage)"
    Then the case should be moved to "CCH"
    Examples:
      | complaintType     |
      | Service           |
      | Ex-Gratia         |
      | Minor Misconduct  |

  Scenario Outline: User moves a COMP case to the Escalated stage
    When I create a "COMP" case and move it to "<complaintType> Escalated"
    Then the case should be moved to "<complaintType> Escalated"
    Examples:
      | complaintType     |
      | Service           |
      | Ex-Gratia         |
      | Minor Misconduct  |

  Scenario Outline: User moves a COMP case to the Draft stage
    When I create a "COMP" case and move it to the "<complaintType> Draft" stage
    Then the case should be moved to the "<complaintType> Draft" stage
    Examples:
      | complaintType             |
      | Service                   |
      | Ex-Gratia Response        |
      | Minor Misconduct Response |

  Scenario: User moves an IEDET case to the Draft stage
    When I create a "IEDET" case and move it to the "Draft" stage
    Then the case should be moved to the "Draft" stage

  Scenario Outline: User moves a COMP case to the QA stage
    When I create a "COMP" case and move it to the "<complaintType> QA" stage
    Then the case should be moved to the "<complaintType> QA" stage
    Examples:
      | complaintType     |
      | Service           |
      | Ex-Gratia         |
      | Minor Misconduct  |

  Scenario Outline: User moves a COMP case to the Send stage
    When I create a "COMP" case and move it to the "<complaintType> Send" stage
    Then the case should be moved to the "<complaintType> Send" stage
    Examples:
      | complaintType     |
      | Service           |
      | Ex-Gratia         |
      | Minor Misconduct  |

  Scenario: User moves an IEDET case to the Send stage
    When I create a "IEDET" case and move it to the "Send" stage
    Then the case should be moved to the "Send" stage

  @COMPRegression @Smoketests
  Scenario Outline: User is able to close a COMP case
    When I create a "COMP" case and move it to the "Complaint Closed (from <complaintType> Send)" stage
    Then the case should be closed
    Examples:
    | complaintType     |
    | Service           |
    | Ex-Gratia         |
    | Minor Misconduct  |

  @COMPRegression
  Scenario: User is able to close an IEDET case
    When I create a "IEDET" case and move it to "Case Closed"
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