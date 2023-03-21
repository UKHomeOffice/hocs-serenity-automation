Feature: PSU End to End

  @ComplaintsWorkflow @IEDETRegression @IEDETComplaint
  Scenario: User completes a IEDET PSU complaint case
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "PSU Case Closed" stage
    Then the case should be closed

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario Outline: User completes a UKVI PSU complaint case
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "PSU_CLOSED" stage
    Then the case should be closed
    When I logout of the application
    Examples:
      | caseType |
      | COMP       |
      | COMP2      |
      | COMP2DIRECT      |

  @ComplaintsWorkflow @BFPSURegression @BFComplaints
  Scenario Outline: User completes a BF PSU complaint case
    Given I am logged into "CS" as user "BF_USER"
    When I create a "<caseType>" case and move it to the "PSU_Closed" stage
    Then the case should be closed
    Examples:
      | caseType |
      | BF       |

