Feature: PSU Registration

    #     IEDET COMPLAINTS and UKVI COMPLAINTS

  @ComplaintsWorkflow @IEDETRegression @IEDETComplaints
  Scenario: User attempts to progress a IEDET PSU case without entering a PSU reference
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "PSU Registration" stage
    Then When I attempt to continue without selecting a PSU Reference an error message is displayed

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario Outline: User attempts to progress a UKVI PSU case without entering a PSU reference
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "<caseStage>" stage
    Then When I attempt to continue without selecting a PSU Reference an error message is displayed

    Examples:
      | caseStage        | caseType    |
      | PSU_Registration | COMP        |
      | PSU_Registration | COMP2       |
      | PSU_Registration | COMP2DIRECT |

