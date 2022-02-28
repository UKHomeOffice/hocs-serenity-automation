@ComplaintsEndToEnd @ComplaintsWorkflow @Complaints
Feature: Complaints End To End

#     UKVI STAGE 1 COMPLAINTS

  Scenario: User creates a UKVI stage 1 complaint case and it starts at the Registration stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Registration" stage
    Then the case should be moved to the "Registration" stage

  Scenario Outline: User moves a UKVI stage 1 complaint case to the Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case for a "<complaintType>" complaint and move it to "<complaintType> Triage"
    Then the case should be moved to the "<complaintType> Triage" stage
    Examples:
      | complaintType    |
      | Service          |
      | Ex-Gratia        |
      | Minor Misconduct |

  Scenario Outline: User moves a UKVI stage 1 complaint case to CCH stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to "CCH"
    When I create a "COMP" case for a "<complaintType>" complaint and move it to "CCH" stage
    Then the case should be moved to "CCH"
    Examples:
      | complaintType    |
      | Service          |
      | Ex-Gratia        |
      | Minor Misconduct |

  Scenario Outline: User moves a UKVI stage 1 complaint case to the Escalated stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case for a "<complaintType>" complaint and move it to "<complaintType> <targetStage>" stage
    Then the case should be moved to "<complaintType> <targetStage>" stage
    Examples:
      | complaintType    | targetStage |
      | Service          | Escalated   |
      | Ex-Gratia        | Escalate    |
      | Minor Misconduct | Escalate    |

  Scenario Outline: User moves a UKVI stage 1 complaint case to the Draft stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case for a "<complaintType>" complaint and move it to "<complaintType> Draft"
    Then the case should be moved to "<complaintType> <targetStage>" stage
    Examples:
      | complaintType    | targetStage    |
      | Service          | Draft          |
      | Ex-Gratia        | Response Draft |
      | Minor Misconduct | Response Draft |

  Scenario Outline: User moves a UKVI stage 1 complaint case to the QA stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case for a "<complaintType>" complaint and move it to "<complaintType> QA"
    Then the case should be moved to the "<complaintType> QA" stage
    Examples:
      | complaintType    |
      | Service          |
      | Ex-Gratia        |
      | Minor Misconduct |

  Scenario Outline: User moves a UKVI stage 1 complaint case to the Send stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case for a "<complaintType>" complaint and move it to "<complaintType> Send"
    Then the case should be moved to the "<complaintType> Send" stage
    Examples:
      | complaintType    |
      | Service          |
      | Ex-Gratia        |
      | Minor Misconduct |

  @ComplaintsRegression @Smoketests
  Scenario Outline: User is able to close a UKVI stage 1 complaint case
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case for a "<complaintType>" complaint and move it to "Complaint Closed"
    Then the case should be closed
    Examples:
      | complaintType    |
      | Service          |
      | Ex-Gratia        |
      | Minor Misconduct |


#     UKVI STAGE 2 COMPLAINTS

  Scenario: User creates a UKVI stage 2 complaint case and it starts at the Registration stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Stage 2 Registration" stage
    Then the case should be moved to the "Stage 2 Registration" stage

  Scenario Outline: User moves a COMP case to the Stage 2 Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Stage 2 <complaintType> Triage" stage
    Then the case should be moved to the "Stage 2 <complaintType> Triage" stage
    Examples:
      | complaintType |
      | Service       |
      | Ex-Gratia     |
      | MM            |

  Scenario Outline: User moves a UKVI stage 2 complaint case to the Stage 2 Draft stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Stage 2 <complaintType> Draft" stage
    Then the case should be moved to the "Stage 2 <complaintType> Draft" stage
    Examples:
      | complaintType      |
      | Service            |
      | Ex-Gratia Response |
      | MM Response        |

  Scenario Outline: User moves a UKVI stage 2 complaint case to the Stage 2 QA stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Stage 2 <complaintType> QA" stage
    Then the case should be moved to the "Stage 2 <complaintType> QA" stage
    Examples:
      | complaintType |
      | Service       |
      | Ex-Gratia     |
      | MM            |

  Scenario Outline: User moves a UKVI stage 2 complaint case to the Stage 2 Send stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Stage 2 <complaintType> Send" stage
    Then the case should be moved to the "Stage 2 <complaintType> Send" stage
    Examples:
      | complaintType |
      | Service       |
      | Ex-Gratia     |
      | MM            |

  @ComplaintsRegression
  Scenario Outline: User is able to close a UKVI stage 2 complaint case
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case for a "<complaintType>" complaint and move it to "Stage 2 Complaint Closed" stage
    Then the case should be closed
    Examples:
      | complaintType    |
      | Service          |
      | Ex-Gratia        |
      | Minor Misconduct |


#     IEDET COMPLAINTS

  Scenario: User creates a IEDET complaint case and it starts at the Registration stage
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "Registration" stage
    Then the case should be moved to the "Registration" stage

  Scenario: User moves an IEDET complaint case to the Triage stage
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "Triage" stage
    Then the case should be moved to the "Triage" stage

  Scenario: User moves an IEDET complaint case to the Draft stage
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "Draft" stage
    Then the case should be moved to the "Draft" stage

  Scenario: User moves an IEDET complaint case to the Send stage
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "Send" stage
    Then the case should be moved to the "Send" stage

  @ComplaintsRegression
  Scenario: User is able to close an IEDET complaint case
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to "Case Closed"
    Then the case should be closed


#     SMC COMPLAINTS

  Scenario: User creates a SMC complaint case and it starts at the Registration stage
    Given I am logged into "CS" as user "SMC_USER"
    When I create a "SMC" case and move it to the "Registration" stage
    Then the case should be moved to the "Registration" stage

  Scenario: User moves an SMC complaint case to the Triage stage
    Given I am logged into "CS" as user "SMC_USER"
    When I create a "SMC" case and move it to the "Triage" stage
    Then the case should be moved to the "Triage" stage

  Scenario: User moves an SMC complaint case to the Send stage
    Given I am logged into "CS" as user "SMC_USER"
    When I create a "SMC" case and move it to the "Send" stage
    Then the case should be moved to the "Send" stage

  @ComplaintsRegression
  Scenario: User is able to close an SMC complaint case
    Given I am logged into "CS" as user "SMC_USER"
    When I create a "SMC" case and move it to "Case Closed"
    Then the case should be closed


#     BF COMPLAINTS

  Scenario: User creates a BF complaint case and it starts at the Registration stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case and move it to the "Registration" stage
    Then the case should be moved to the "Case Registration" stage

  Scenario Outline: User moves a BF complaint case to the Triage stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case for a "<complaintType>" complaint and move it to "Triage"
    Then the case should be moved to the "Case Triage" stage
    Examples:
      | complaintType    |
      | Service          |
      | Minor Misconduct |

  Scenario Outline: User moves a BF complaint case to the Escalated stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case for a "<complaintType>" complaint and move it to "<targetStage>" stage
    Then the case should be moved to "<targetStage>" stage
    Examples:
      | complaintType    | targetStage      |
      | Service          | Escalated to WFM |
      | Minor Misconduct | Escalated to WFM |

  Scenario Outline: User moves a BF complaint case to the Draft stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case for a "<complaintType>" complaint and move it to "Draft"
    Then the case should be moved to "<targetStage>" stage
    Examples:
      | complaintType    | targetStage |
      | Service          | Draft       |
      | Minor Misconduct | Draft       |

  Scenario Outline: User moves a BF complaint case to the QA stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case for a "<complaintType>" complaint and move it to "QA"
    Then the case should be moved to the "QA" stage
    Examples:
      | complaintType    |
      | Service          |
      | Minor Misconduct |

  Scenario Outline: User moves a BF complaint to the Send stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case for a "<complaintType>" complaint and move it to "Send"
    Then the case should be moved to the "Send draft response" stage
    Examples:
      | complaintType    |
      | Service          |
      | Minor Misconduct |

  @ComplaintsRegression @Smoketests
  Scenario Outline: User is able to close a BF stage 1 complaint case
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case for a "<complaintType>" complaint and move it to "Closed"
    Then the case should be closed
    Examples:
      | complaintType    |
      | Service          |
      | Minor Misconduct |


#     BF STAGE 2 COMPLAINTS

  @ComplaintsRegression @Smoketests
  Scenario: User is able to close a BF stage 2 complaint case
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF2" case and move it to the "Closed" stage
    Then the case should be closed