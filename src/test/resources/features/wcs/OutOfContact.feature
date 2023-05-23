@OutOfContact @WCS
Feature: Out Of Contact

  Background:
    Given I am logged into "WCS" as user "WCS_USER"

  @Workflow @WCSRegression
  Scenario: User sends a claim to the Pre-offer team and back to the original team
    When I create a single "WCS" claim
    And I select the summary tab
    And the summary should display the owning team as "WCS Registration Team"
    And I select the Out of Contact tab
    And I select Pre-Offer in the Out of Contact Tab
    And the summary should display the owning team as "Pre-offer out of contact"
    And I click to view the claim in the "Pre-offer out of contact" workstack
    Then the created case should be visible in the workstack
    And I click the link for the current case in the workstack
    And I select the Out of Contact tab
    And I click the "Submit" button
    And the summary should display the owning team as "WCS Registration Team"
    And I click to view the claim in the "WCS Registration Team" workstack
    Then the created case should be visible in the workstack

  @Workflow @WCSRegression
  Scenario: User sends a claim to the Post-offer team and back to the original team
    When I create a single "WCS" claim
    And I select the summary tab
    And the summary should display the owning team as "WCS Registration Team"
    And I select the Out of Contact tab
    And I select Post-Offer in the Out of Contact Tab
    And the summary should display the owning team as "Post-offer out of contact"
    And I click to view the claim in the "Post-offer out of contact" workstack
    Then the created case should be visible in the workstack
    And I click the link for the current case in the workstack
    And I select the Out of Contact tab
    And I click the "Submit" button
    And the summary should display the owning team as "WCS Registration Team"
    And I click to view the claim in the "WCS Registration Team" workstack
    Then the created case should be visible in the workstack
