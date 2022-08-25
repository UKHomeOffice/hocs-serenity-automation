@Ex-GratiaTab @Complaints
Feature: Ex-Gratia Tab

#     BF COMPLAINTS CASE

  @BFRegression @BFComplaints
  Scenario: User is able to record that a BF Stage 1 complainant has accepted the Ex-Gratia offer after a case is closed
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF" case at the "Case Closed" stage
    And I select the Ex-Gratia tab
    And I select the update Ex-Gratia details hypertext
    And I select that the complainant has accepted the offer
    And I click the "Submit" button
    Then an Ex-Gratia updated log should be visible in the case timeline

    #    HOCS-4256
  @BFRegression @BFComplaints
  Scenario: User is able to record that a BF Stage 2 complainant has accepted the Ex-Gratia offer after a case is closed
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF2" case at the "Case Closed" stage
    And I select the Ex-Gratia tab
    And I select the update Ex-Gratia details hypertext
    And I select that the complainant has accepted the offer
    And I click the "Submit" button
    Then an Ex-Gratia updated log should be visible in the case timeline

#     POGR COMPLAINTS CASE

  @POGRRegression @POGRComplaints
  Scenario Outline: User is able to record Ex-Gratia payment details in the Ex-Gratia tab of a POGR case
    Given I am logged into "CS" as user "POGR_USER"
    When I get a POGR case with "<businessArea>" as the Business Area at the "Investigation" stage
    And I select the Ex-Gratia tab
    And I enter details into the Ex-Gratia tab for a POGR case
    Then an Ex-Gratia updated log should be visible in the case timeline
    Examples:
    | businessArea  |
    | HMPO          |
    | GRO           |