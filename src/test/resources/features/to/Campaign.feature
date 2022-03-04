@Campaign @TO
Feature: Campaign

  Background:
    Given I am logged into "CS" as user "TO_USER"
    And I get a "TO" case at "Campaign" stage


  @TORegression
  Scenario: As a Campaign user, I want to be able to return the case to Triage, so the case can progress
    When I select to take the case out of the campaign and move it to triage
    Then the case should be returned to the "Triage" stage
    And the case should still be owned by the correct Treat Official team for the selected business area

  @TORegression
  Scenario: As a Campaign user, I want to be able to send the case to Draft, so the case can progress
    When I set an Enquiry Subject and Reason
    And I select a Business Unit Type and corresponding Business Unit
    And I select to take the case out of the campaign and move it to draft
    Then the case should be moved to the "Draft" stage
    And the case should still be owned by the correct Treat Official team for the selected business area

  @TORegression
  Scenario: As a Campaign user, I want to be able to change the business area of the case, so that the correct team can casework it
    When I open the "Case Details" accordion section
    And I change the Business Area of the case
    Then the case should be moved to the correct Treat Official team for the new business area