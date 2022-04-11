@Investigation @Complaints
Feature: Investigation

  Background:
    Given I am logged into "CS" as user "POGR_USER"
    And I get a "POGR" case at the "Data Input" stage

  Scenario: User is able to complete the Investigation stage for a POGR case with HMPO as the business area
    When I complete the Data Input stage with "HMPO" as the business area
    And I load and claim the current case
    And I "Accept" the case at the Investigation stage
    And I click the "Continue" button
    And I complete the "All Information Collected - Respond" action at the Investigation stage
    Then the case should be moved to the "Draft" stage
    And the summary should display the owning team as "HMPO Complaints"
#    And the read-only Case Details accordion should contain all case information entered during the "Investigation" stage

  Scenario: User is able to complete the Investigation stage for a POGR case with GRO as the business area
    When I complete the Data Input stage with "GRO" as the business area
    And I load and claim the current case
    And I "Accept" the case at the Investigation stage
    And I click the "Continue" button
    And I complete the "All Information Collected - Respond" action at the Investigation stage
    Then the case should be moved to the "Draft" stage
    And the POGR case should be assigned to the investigating team selected at Data Input
#    And the read-only Case Details accordion should contain all case information entered during the "Investigation" stage

  Scenario Outline: User is able to close a POGR case at the Investigation stage
    When I complete the Data Input stage with "<businessArea>" as the business area
    And I load and claim the current case
    And I "Accept" the case at the Investigation stage
    And I click the "Continue" button
    And I complete the "No Response - Complete the Case" action at the Investigation stage
    Then the case should be closed
#     And the read-only Case Details accordion should contain all case information entered during the "Investigation" stage
  Examples:
    | businessArea  |
    | HMPO          |
    | GRO           |

