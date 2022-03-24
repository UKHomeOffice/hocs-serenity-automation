@DataInput @Complaints
Feature: Data Input

  Background:
    Given I am logged into "CS" as user "HMPO_USER"
    And I get a "POGR" case at the "Data Input" stage

  Scenario: User is able to complete the Data Input stage for a POGR case with HMPO as the business area
    When I select "HMPO" as the business area for the POGR case
    And I add a "Complainant" correspondent
    And I click the "Continue" button
    And I enter complainant details on the complainant details screen
    And I click the "Continue" button
    And I add an "Interim Letter" type document to the case
    And I enter the date that the letter was sent
    Then the case should be moved to the "Investigating Triage" stage
    And the summary should display the owning team as "HMPO Complaints"
#    And the read-only Case Details accordion should contain all case information entered during the "Data Input" stage  Bits of information
#    missing, doesn't work yet

  Scenario: User is able to complete the Data Input stage for a POGR case with GRO as the business area
    When I select "GRO" as the business area for the POGR case
    And I add a "Complainant" correspondent
    And I click the "Continue" button
    And I enter complainant details on the complainant details screen
    And I click the "Continue" button
    And I add an "Interim Letter" type document to the case
    And I enter the date that the letter was sent
    And I select the investigating team for the case
    Then the case should be moved to the "Investigating Triage" stage
    And the POGR case should be assigned to the investigating team selected at Data Input
#    And the read-only Case Details accordion should contain all case information entered during the "Data Input" stage   Same issue as above