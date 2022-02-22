@DataInput @TO
Feature: Data Input

  Background:
    Given I am logged into "CS" as user "TO_USER"
    And I get a "TO" case at "Data Input" stage

#    Expected failure. Defect HOCS-4353 raised.
  @TOWorkflow @TORegression
  Scenario: As a Data Input user, I want to be able to complete the Data Input stage, so the case can progress to Triaging
    When I select which business area the correspondence is for
    And I select which channel the correspondence was received by
    And I select whether the Home Secretary has an interest in the case
    And I add a "Correspondent" correspondent
    And I confirm the primary correspondent
    And I choose to not add a recipient
    Then the case should be moved to the "Triage" stage
    And the summary should display the owning team as the correct Treat Official team for the selected business area
    And the read-only Case Details accordion should contain all case information entered during the "Data Input" stage
    And the summary should contain the Business Area, Channel Received, Home Secretary Interest selection, and Primary Correspondents details

#    Expected failure. Defect HOCS-4353 raised.
  @TOWorkflow @TORegression
  Scenario: As a Data Input user, I want to be able to enter the intended recipient, so the reply can be correctly personalised
    And I select which business area the correspondence is for
    And I select which channel the correspondence was received by
    And I select whether the Home Secretary has an interest in the case
    And I add a "Third Party Representative" correspondent
    And I confirm the primary correspondent
    When I add a Recipient to the case
    Then the case should be moved to the "Triage" stage
    And the summary should contain the Recipient
    And the read-only Case Details accordion should contain all case information entered during the "Data Input" stage