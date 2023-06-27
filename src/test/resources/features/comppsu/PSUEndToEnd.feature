Feature: PSU End to End

  @ComplaintsWorkflow @IEDETRegression @IEDETComplaint @E2ETests
  Scenario: User completes a IEDET PSU complaint case
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "PSU Case Closed" stage
    Then the case should be closed

  Scenario: Robust End to End flow with IEDET PSU CaseType
    Given I am logged into "CS" as user "IEDET_USER"
    And I choose not to wipe the record data until the end
    When I create a "IEDET" case and move it to the "PSU Case Closed" stage
    Then the case should be closed
    And all case data should be visible in the read-only Case Details accordion
    And the summary tab should display the details entered at various stages
    And I wipe the record data

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints @E2ETests
  Scenario Outline: User completes a UKVI PSU complaint case
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "PSU_CLOSED" stage
    Then the case should be closed
    When I logout of the application
    Examples:
      | caseType    |
      | COMP        |
      | COMP2       |
      | COMP2DIRECT |

  @ComplaintsWorkflow @BFPSURegression @BFComplaints @E2ETests
  Scenario Outline: User completes a BF PSU complaint case
    Given I am logged into "CS" as user "BF_USER"
    When I create a "<caseType>" case and move it to the "PSU_Closed" stage
    Then the case should be closed
    Examples:
      | caseType |
      | BF       |

