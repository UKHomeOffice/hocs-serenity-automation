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
    And I add a "Correspondent" correspondent
    And I confirm the primary correspondentUser dispatches a case

    And I choose to not add a recipient
    Then the case should be moved to the "Triage" stage
    And the summary should display the owning team as the correct Treat Official team for the selected business area
    And the read-only Case Details accordion should contain all case information entered during the "Data Input" stage
    And the summary should contain the Business Area, Channel Received and Primary Correspondents details

#    Expected failure. Defect HOCS-4353 raised.
  @TOWorkflow @TORegression
  Scenario Outline: As a Data Input user, I want to be able to enter th intended recipient, so the reply can be correctly personalised
    And I select which business area the correspondence is for
    And I select which channel the correspondence was received by
    And I add a "Third Party Representative" correspondent
    And I confirm the primary correspondent
    When I add a "<recipientType>" recipient
    Then the case should be moved to the "Triage" stage
    And the summary should contain the "<recipientType>" recipient
    And the read-only Case Details accordion should contain all case information entered during the "Data Input" stage
    Examples:
      | recipientType |
      | Member        |
      | Non-member    |