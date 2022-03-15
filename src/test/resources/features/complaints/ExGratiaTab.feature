@Ex-GratiaTab @Complaints
Feature: Ex-Gratia Tab

  Background:
    Given I am logged into "CS" as user "BF_USER"

#    HOCS-4003
  @ComplaintsRegression2 @BFComplaints
  Scenario: User is able to summarise details of the Ex-Gratia element of a complaint through the Ex-Gratia tab
    When I get a "BF" case at the "Registration" stage
    And I select the Ex-Gratia tab
    And I select the update Ex-Gratia details hypertext
    And I select the "Consolatory" payment type checkbox
    And I enter "£1000" into the amount requested by the complainant field
    And I enter "£900" into the amount requested from business/port field
    And I enter "£800" into the consolatory payment offer sent to the complainant field
    And I enter "£700" into the Ex-Gratia payment offer sent to the complainant field
    And I enter "£600" into the total payment offer sent to the complainant field
    And I select that the complainant has accepted the offer
    And I click the "Submit" button
    Then the Ex-Gratia tab summary should contain the correct values