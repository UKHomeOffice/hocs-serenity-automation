@DataInput @TO
Feature: Data Input

  Background:
    Given I am logged into "CS" as user "TO_USER"
    And I get a "TO" case at "Data Input" stage

  @TOWorkflow @TORegression
  Scenario: As a Data Input user, I want to be able to complete the Data Input, so the case can progress to Triage
    When I select which business area the correspondence is for
    And I select which channel the correspondence was received by
    And I add a "Correspondent" correspondent
    And I confirm the primary correspondent
    And I select an Addressee
    Then the case should be moved to the "Triage" stage
    And the summary should display the owning team as the correct Treat Official team for the selected business area
    And the read-only Case Details accordion should contain all case information entered during the "Data Input" stage
    And the summary should contain the Business Area, Channel Received, Addressee and Primary Correspondent