@Ex-GratiaTab @Complaints
Feature: Ex-Gratia Tab

  Background:
    Given I am logged into "CS" as user "BF_USER"

#    HOCS-4003
  @ComplaintsRegression2 @BFComplaints
  Scenario: User is able to summarise details of the Ex-Gratia element of a BF Stage 1 complaint through the Ex-Gratia tab
    When I get a "BF" case at the "Registration" stage
    And I select the Ex-Gratia tab
    And I select the update Ex-Gratia details hypertext
    And I select the "Consolatory" payment type checkbox
    And I enter "£1000" into the amount requested by the complainant field
    And I enter "£900" into the amount requested from business or port field
    And I enter "£800" into the consolatory payment offer sent to the complainant field
    And I enter "£700" into the Ex-Gratia payment offer sent to the complainant field
    And I enter "£600" into the total payment offer sent to the complainant field
    And I select that the complainant has accepted the offer
    And I click the "Submit" button
    Then the Ex-Gratia tab summary should contain the correct values

  @ComplaintsRegression2 @BFComplaints
  Scenario: User is able to record that a BF Stage 1 complainant has accepted the Ex-Gratia offer after a case is closed
    When I get a "BF" case at the "Case Closed" stage
    And I select the Ex-Gratia tab
    And I select the update Ex-Gratia details hypertext
    And I select that the complainant has accepted the offer
    And I click the "Submit" button
    Then the Ex-Gratia tab summary should show that the complainant has accepted the offer

#    HOCS-4255
  @ComplaintsRegression2 @BFComplaints
  Scenario: User is able to summarise details of the Ex-Gratia element of a BF Stage 2 complaint through the Ex-Gratia tab
    When I get a "BF2" case at the "Registration" stage
    And I select the Ex-Gratia tab
    And I select the update Ex-Gratia details hypertext
    And I select the "Consolatory" payment type checkbox
    And I enter "£1000" into the amount requested by the complainant field
    And I enter "£900" into the amount requested from business or port field
    And I enter "£800" into the consolatory payment offer sent to the complainant field
    And I enter "£700" into the Ex-Gratia payment offer sent to the complainant field
    And I enter "£600" into the total payment offer sent to the complainant field
    And I select that the complainant has accepted the offer
    And I click the "Submit" button
    Then the Ex-Gratia tab summary should contain the correct values

    #    HOCS-4256
  @ComplaintsRegression2 @BFComplaints
  Scenario: User is able to record that a BF Stage 2 complainant has accepted the Ex-Gratia offer after a case is closed
    When I get a "BF2" case at the "Case Closed" stage
    And I select the Ex-Gratia tab
    And I select the update Ex-Gratia details hypertext
    And I select that the complainant has accepted the offer
    And I click the "Submit" button
    Then the Ex-Gratia tab summary should show that the complainant has accepted the offer
