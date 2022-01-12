@Triage @TO
Feature: Triage

  Background:
    Given I am logged into "CS" as user "TO_USER"
    And I get a "TO" case at "Triage" stage

#    Expected failure. Defect HOCS-4353 raised.
  @TOWorkflow @TORegression
  Scenario: As a Data Input user, I want to be able to complete the Traige stage, so the case can progress to Drafting
    When I set an Enquiry Subject and Reason
    And I select a Business Unit Type and corresponding Business Unit
    And I confirm the case is ready to be drafted
    Then the case should be moved to the "Draft" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the read-only Case Details accordion should contain all case information entered during the "Data Input" stage
    And the summary should contain the Enquiry Reason and Enquiry Subject
