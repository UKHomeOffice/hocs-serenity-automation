@ComplaintsEndToEnd @ComplaintsWorkflow @Complaints
Feature: Complaints End To End

#     UKVI STAGE 1 COMPLAINTS

  @UKVIComplaints
  Scenario: User creates a UKVI stage 1 complaint case and it starts at the Registration stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Registration" stage
    Then the case should be moved to the "Registration" stage

  @UKVIComplaints
  Scenario Outline: User moves a UKVI stage 1 complaint case to the Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case for a "<complaintType>" complaint and move it to "<complaintType> Triage"
    Then the case should be moved to the "<complaintType> Triage" stage
    Examples:
      | complaintType    |
      | Service          |
      | Ex-Gratia        |
      | Minor misconduct |

  @UKVIComplaints
  Scenario Outline: User moves a UKVI stage 1 complaint case to CCH stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to "CCH"
    When I create a "COMP" case for a "<complaintType>" complaint and move it to "CCH" stage
    Then the case should be moved to "CCH"
    Examples:
      | complaintType    |
      | Service          |
      | Ex-Gratia        |
      | Minor misconduct |

  @UKVIComplaints
  Scenario Outline: User moves a UKVI stage 1 complaint case to the Escalated stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case for a "<complaintType>" complaint and move it to "<complaintType> <targetStage>" stage
    Then the case should be moved to "<complaintType> <targetStage>" stage
    Examples:
      | complaintType    | targetStage |
      | Service          | Escalated   |
      | Ex-Gratia        | Escalate    |
      | Minor misconduct | Escalate    |

  @UKVIComplaints
  Scenario Outline: User moves a UKVI stage 1 complaint case to the Draft stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case for a "<complaintType>" complaint and move it to "<complaintType> Draft"
    Then the case should be moved to "<complaintType> <targetStage>" stage
    Examples:
      | complaintType    | targetStage    |
      | Service          | Draft          |
      | Ex-Gratia        | Response Draft |
      | Minor misconduct | Response Draft |

  @UKVIComplaints
  Scenario Outline: User moves a UKVI stage 1 complaint case to the QA stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case for a "<complaintType>" complaint and move it to "<complaintType> QA"
    Then the case should be moved to the "<complaintType> QA" stage
    Examples:
      | complaintType    |
      | Service          |
      | Ex-Gratia        |
      | Minor misconduct |

  @UKVIComplaints
  Scenario Outline: User moves a UKVI stage 1 complaint case to the Send stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case for a "<complaintType>" complaint and move it to "<complaintType> Send"
    Then the case should be moved to the "<complaintType> Send" stage
    Examples:
      | complaintType    |
      | Service          |
      | Ex-Gratia        |
      | Minor misconduct |

  @COMPRegression @UKVIComplaints @E2ETests
  Scenario Outline: User is able to close a UKVI stage 1 complaint case
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case for a "<complaintType>" complaint and move it to "Complaint Closed"
    Then the case should be closed
    Examples:
      | complaintType    |
      | Service          |
      | Ex-Gratia        |
      | Minor misconduct |


#     UKVI STAGE 2 COMPLAINTS

  @UKVIComplaints
  Scenario: User creates a UKVI stage 2 complaint case and it starts at the Registration stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Stage 2 Registration" stage
    Then the case should be moved to the "UKVI Stage 2 Registration" stage

  @UKVIComplaints
  Scenario Outline: User moves a COMP case to the Stage 2 Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Stage 2 <complaintType> Triage" stage
    Then the case should be moved to the "UKVI Stage 2 <complaintType> Triage" stage
    Examples:
      | complaintType |
      | Service       |
      | Ex-Gratia     |
      | MM            |

  @UKVIComplaints
  Scenario Outline: User moves a UKVI stage 2 complaint case to the Stage 2 Draft stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Stage 2 <complaintType> Draft" stage
    Then the case should be moved to the "UKVI Stage 2 <complaintType> Draft" stage
    Examples:
      | complaintType      |
      | Service            |
      | Ex-Gratia Response |
      | MM Response        |

  @UKVIComplaints
  Scenario Outline: User moves a UKVI stage 2 complaint case to the Stage 2 QA stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Stage 2 <complaintType> QA" stage
    Then the case should be moved to the "UKVI Stage 2 <complaintType> QA" stage
    Examples:
      | complaintType |
      | Service       |
      | Ex-Gratia     |
      | MM            |

  @UKVIComplaints
  Scenario Outline: User moves a UKVI stage 2 complaint case to the Stage 2 Send stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Stage 2 <complaintType> Send" stage
    Then the case should be moved to the "UKVI Stage 2 <complaintType> Send" stage
    Examples:
      | complaintType |
      | Service       |
      | Ex-Gratia     |
      | MM            |

  @COMPRegression @UKVIComplaints @E2ETests
  Scenario Outline: User is able to close a UKVI stage 2 complaint case
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case for a "<complaintType>" complaint and move it to "Stage 2 Complaint Closed" stage
    Then the case should be closed
    Examples:
      | complaintType    |
      | Service          |
      | Ex-Gratia        |
      | Minor misconduct |

  @COMPRegression @UKVIComplaints @E2ETests
  Scenario Outline: User is able to close a Direct UKVI stage 2 complaint case
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2DIRECT" case for a "<complaintType>" complaint and move it to "Complaint Closed"
    Then the case should be closed
    Examples:
      | complaintType    |
      | Service          |
      | Ex-Gratia        |
      | Minor misconduct |


#     IEDET COMPLAINTS

  @IEDETComplaints
  Scenario: User creates a IEDET complaint case and it starts at the Registration stage
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "IE Detention Registration" stage
    Then the case should be moved to the "IE Detention Registration" stage

  @IEDETComplaints
  Scenario: User moves an IEDET complaint case to the Triage stage
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "IE Detention Triage" stage
    Then the case should be moved to the "IE Detention Triage" stage

  @IEDETComplaints
  Scenario: User moves an IEDET complaint case to the Draft stage
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "IE Detention Draft" stage
    Then the case should be moved to the "IE Detention Draft" stage

  @IEDETComplaints
  Scenario: User moves an IEDET complaint case to the Send stage
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "IE Detention Outcome" stage
    Then the case should be moved to the "IE Detention Outcome" stage

  @IEDETRegression @IEDETComplaints @E2ETests
  Scenario: User is able to close an IEDET complaint case
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to "Case Closed"
    Then the case should be closed

  #HOCS-6694
  @E2ETests
  Scenario: Robust End to End flow with IEDET CaseType
    Given I am logged into "CS" as user "IEDET_USER"
    And I choose not to wipe the record data until the end
    When I create a "IEDET" case and move it to "Case Closed"
    Then the case should be closed
    And all case data should be visible in the read-only Case Details accordion
    And the summary tab should display the details entered at various stages
    And I wipe the record data

#     BF COMPLAINTS

  @BFComplaints
  Scenario: User creates a BF complaint case and it starts at the Registration stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case and move it to the "Registration" stage
    Then the case should be moved to the "Case Registration" stage

  @BFComplaints
  Scenario Outline: User moves a BF complaint case to the Triage stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case for a "<complaintType>" complaint and move it to "Triage"
    Then the case should be moved to the "Case Triage" stage
    Examples:
      | complaintType    |
      | Service          |
      | Minor Misconduct |

  @BFComplaints
  Scenario Outline: User moves a BF complaint case to the Escalated stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case for a "<complaintType>" complaint and move it to "<targetStage>" stage
    Then the case should be moved to "<targetStage>" stage
    Examples:
      | complaintType    | targetStage      |
      | Service          | Escalated to WFM |
      | Minor Misconduct | Escalated to WFM |

  @BFComplaints
  Scenario Outline: User moves a BF complaint case to the Draft stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case for a "<complaintType>" complaint and move it to "Draft"
    Then the case should be moved to "<targetStage>" stage
    Examples:
      | complaintType    | targetStage |
      | Service          | Draft       |
      | Minor Misconduct | Draft       |

  @BFComplaints
  Scenario Outline: User moves a BF complaint case to the QA stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case for a "<complaintType>" complaint and move it to "QA"
    Then the case should be moved to the "QA" stage
    Examples:
      | complaintType    |
      | Service          |
      | Minor Misconduct |

  @BFComplaints
  Scenario Outline: User moves a BF complaint to the Send stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case for a "<complaintType>" complaint and move it to "Send"
    Then the case should be moved to the "Send draft response" stage
    Examples:
      | complaintType    |
      | Service          |
      | Minor Misconduct |

  @BFRegression @BFComplaints @E2ETests
  Scenario Outline: User is able to close a BF stage 1 complaint case
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case for a "<complaintType>" complaint and move it to "Case Closed"
    Then the case should be closed
    Examples:
      | complaintType    |
      | Service          |
      | Minor misconduct |


#     BF STAGE 2 COMPLAINTS

  @BFComplaints
  Scenario: User escalates a BF complaint case to stage 2 and it starts at the Registration stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF2" case and move it to the "Registration" stage
    Then the case should be moved to the "Case Registration (Stage 2)" stage

  @BFComplaints
  Scenario: User escalates a BF complaint case to stage 2 and moves it to the Triage stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF2" case and move it to the "Triage" stage
    Then the case should be moved to the "Case Triage (Stage 2)" stage

  @BFComplaints
  Scenario: User escalates a BF complaint case to stage 2 and moves it to the Escalated stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF2" case and move it to the "Escalated to WFM" stage
    Then the case should be moved to the "Escalated to WFM (Stage 2)" stage

  @BFComplaints
  Scenario: User escalates a BF complaint case to stage 2 and moves it to the Draft stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF2" case and move it to the "Draft" stage
    Then the case should be moved to the "Draft (Stage 2)" stage

  @BFComplaints
  Scenario: User escalates a BF complaint case to stage 2 and moves it to the QA stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF2" case and move it to the "QA" stage
    Then the case should be moved to the "QA (Stage 2)" stage

  @BFComplaints
  Scenario: User escalates a BF complaint case to stage 2 and moves it to the Send stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF2" case and move it to the "Send" stage
    Then the case should be moved to the "Send (Stage 2)" stage

  @BFRegression @BFComplaints @E2ETests
  Scenario: User is able to close a BF stage 2 complaint case
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF2" case and move it to the "Case Closed" stage
    Then the case should be closed


#     POGR COMPLAINTS

  @POGRComplaints
  Scenario: User creates a POGR complaint case and it should be at the Data Input stage
    Given I am logged into "CS" as user "POGR_USER"
    When I create a "POGR" case and move it to the "Data Input" stage
    Then the case should be moved to the "Data Input" stage

  @POGRComplaints
  Scenario: User moves a POGR complaint case to the Investigation stage
    Given I am logged into "CS" as user "POGR_USER"
    When I create a "POGR" case and move it to the "Investigation" stage
    Then the case should be moved to the "Investigation" stage

  @POGRComplaints
  Scenario: User moves a POGR complaint case to the Draft stage
    Given I am logged into "CS" as user "POGR_USER"
    When I create a "POGR" case and move it to the "Draft" stage
    Then the case should be moved to the "Draft" stage

  @POGRComplaints
  Scenario: User moves a POGR complaint case to the QA stage
    Given I am logged into "CS" as user "POGR_USER"
    When I create a "POGR" case and move it to the "QA" stage
    Then the case should be moved to the "QA" stage

  @POGRComplaints
  Scenario: User moves a POGR complaint case to the Dispatch stage
    Given I am logged into "CS" as user "POGR_USER"
    When I create a "POGR" case and move it to the "Dispatch" stage
    Then the case should be moved to the "Dispatch" stage

  @POGRRegression @POGRComplaints @E2ETests
  Scenario: User is able to close a POGR complaint case
    Given I am logged into "CS" as user "POGR_USER"
    When I create a "POGR" case and move it to the "Case Closed" stage
    Then the case should be closed


  Scenario: End to End workflow for HMPO POGR complaint case
    Given I am logged into "CS" as user "POGR_USER"
    And I choose not to wipe the record data until the end
    When I get a "POGR" case with "HMPO" as the Business Area at the "Case Closed" stage
    Then the case should be closed
    And all case data should be visible in the read-only Case Details accordion
    And the summary tab should display the details entered at various stages
    And I wipe the record data

  Scenario: End to End workflow for GRO POGR complaint case
    Given I am logged into "CS" as user "POGR_USER"
    And I choose not to wipe the record data until the end
    When I get a "POGR" case with "GRO" as the Business Area at the "Case Closed" stage
    Then the case should be closed
    And all case data should be visible in the read-only Case Details accordion
    And the summary tab should display the details entered at various stages
    And I wipe the record data

#     POGR (STAGE 2) COMPLAINTS

  @POGRComplaints
  Scenario: User creates a POGR stage 2 complaint case and it should be at the Data Input stage
    Given I am logged into "CS" as user "POGR_USER"
    When I create a "POGR2" case and move it to the "Data Input" stage
    Then the case should be moved to the "Data Input" stage

  @POGRComplaints
  Scenario: User moves a POGR stage 2 complaint case to the Investigation stage
    Given I am logged into "CS" as user "POGR_USER"
    When I create a "POGR2" case and move it to the "Investigation" stage
    Then the case should be moved to the "Investigation" stage

  @POGRComplaints
  Scenario: User moves a POGR stage 2 complaint case to the Draft stage
    Given I am logged into "CS" as user "POGR_USER"
    When I create a "POGR2" case and move it to the "Draft" stage
    Then the case should be moved to the "Draft" stage

  @POGRComplaints
  Scenario: User moves a POGR stage 2 complaint case to the QA stage
    Given I am logged into "CS" as user "POGR_USER"
    When I create a "POGR2" case and move it to the "QA" stage
    Then the case should be moved to the "QA" stage

  @POGRComplaints
  Scenario: User moves a POGR stage 2 complaint case to the Dispatch stage
    Given I am logged into "CS" as user "POGR_USER"
    When I create a "POGR2" case and move it to the "Dispatch" stage
    Then the case should be moved to the "Dispatch" stage

  @POGRRegression @POGRComplaints @E2ETests
  Scenario: User is able to close a POGR stage 2 complaint case
    Given I am logged into "CS" as user "POGR_USER"
    When I create a "POGR2" case and move it to the "Case Closed" stage
    Then the case should be closed

  Scenario: End to End workflow for HMPO POGR stage 2 complaint case
    Given I am logged into "CS" as user "POGR_USER"
    And I choose not to wipe the record data until the end
    When I get a "POGR2" case with "HMPO" as the Business Area at the "Case Closed" stage
    Then the case should be closed
    And all case data should be visible in the read-only Case Details accordion
    And the summary tab should display the details entered at various stages
    And I wipe the record data

  Scenario: End to End workflow for GRO POGR stage 2 complaint case
    Given I am logged into "CS" as user "POGR_USER"
    And I choose not to wipe the record data until the end
    When I get a "POGR2" case with "GRO" as the Business Area at the "Case Closed" stage
    Then the case should be closed
    And all case data should be visible in the read-only Case Details accordion
    And the summary tab should display the details entered at various stages
    And I wipe the record data

#  SMC workflow cancelled. Steps and code might be useful for future work implementing PSU specific sub-workflow into other complaints workflows

#     SMC COMPLAINTS

  @SMCComplaints
  Scenario: User creates a SMC complaint case and it starts at the Registration stage
    Given I am logged into "CS" as user "SMC_USER"
    When I create a "SMC" case and move it to the "Registration" stage
    Then the case should be moved to the "Registration" stage

  @SMCComplaints
  Scenario: User moves an SMC complaint case to the Triage stage
    Given I am logged into "CS" as user "SMC_USER"
    When I create a "SMC" case and move it to the "Triage" stage
    Then the case should be moved to the "Triage" stage

  @SMCComplaints
  Scenario: User moves an SMC complaint case to the Send stage
    Given I am logged into "CS" as user "SMC_USER"
    When I create a "SMC" case and move it to the "Send" stage
    Then the case should be moved to the "Send" stage

  @SMCComplaints
  Scenario: User is able to close an SMC complaint case
    Given I am logged into "CS" as user "SMC_USER"
    When I create a "SMC" case and move it to "Case Closed"
    Then the case should be closed