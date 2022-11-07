Feature: PSU Registration

    #     IEDET COMPLAINTS

  @ComplaintsWorkflow @IEDETRegression @IEDETComplaints
  Scenario: User attempts to progress a IEDET PSU case without entering a PSU reference
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "PSU Registration" stage
    Then When I attempt to continue without selecting a PSU Reference an error message is displayed