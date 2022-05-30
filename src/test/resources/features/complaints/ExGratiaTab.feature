@Ex-GratiaTab @Complaints
Feature: Ex-Gratia Tab

  Background:
    Given I am logged into "CS" as user "BF_USER"

  @ComplaintsRegression2 @BFComplaints
  Scenario: User is able to record that a BF Stage 1 complainant has accepted the Ex-Gratia offer after a case is closed
    When I get a "BF" case at the "Case Closed" stage
    And I select the Ex-Gratia tab
    And I select the update Ex-Gratia details hypertext
    And I select that the complainant has accepted the offer
    And I click the "Submit" button
    Then the Ex-Gratia tab summary should show that the complainant has accepted the offer

    #    HOCS-4256
  @ComplaintsRegression2 @BFComplaints
  Scenario: User is able to record that a BF Stage 2 complainant has accepted the Ex-Gratia offer after a case is closed
    When I get a "BF2" case at the "Case Closed" stage
    And I select the Ex-Gratia tab
    And I select the update Ex-Gratia details hypertext
    And I select that the complainant has accepted the offer
    And I click the "Submit" button
    Then the Ex-Gratia tab summary should show that the complainant has accepted the offer