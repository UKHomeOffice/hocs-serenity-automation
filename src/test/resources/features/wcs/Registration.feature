@Registration @WCS
Feature: Registration

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I create a single "WCS" claim

  @Navigation
  Scenario: User selects yes to Has Task Force confirmed status
    When I select "Yes" for has Task Force confirmed status question
    Then the "Registration" page should be displayed

  @Navigation
  Scenario: User selects no to Has Task Force confirmed status
    When I select "No" for has Task Force confirmed status question
    Then the "Registration Identity Confirmation" page should be displayed

  @Validation
  Scenario: User does not enter an answer to the Task Force question before continuing
    When I click the "Confirm" button
    Then an error message should be displayed as I have not confirmed if the status has been confirmed by the Task Force

  @Validation
  Scenario: User saves a claim without completing the mandatory Case Details fields
    And I progress to the Registration page
    And I select that I can confirm the claimants ID
    When I click the "Confirm" button
    Then an error message for each mandatory claim details field should be displayed as I have not completed them

  @Validation
  Scenario: User presses Confirm without selecting a value for the 'able to confirm claimants ID?' question
    And I progress to the Registration page
    And I complete the required case details fields
    When I click the "Confirm" button
    Then an error message is displayed as I have not confirmed the claimants status

  @Navigation
  Scenario: User completes the Registration page
    When I progress to the Registration page
    And I complete the Registration page
    Then the "Registration - Choose next team" page should be displayed

  @Workflow @WCSRegression
  Scenario: User chooses to send the claim to Eligibility
    When I progress to the Registration page
    And I complete the Registration page
    And I choose to send the claim to Eligibility
    Then the claim should be moved to the "Eligibility" stage

  @Workflow @WCSRegression
  Scenario: User chooses to send the claim to Triage
    When I progress to the Registration page
    And I complete the Registration page
    And I choose to send the claim to Triage
    Then the claim should be moved to the "Triage" stage

  @Validation
  Scenario: User does not select which team to send the claim to
    When I progress to the Registration page
    And I complete the Registration page
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected a team to send the claim on to

  @Validation
  Scenario: User presses Confirm without selecting a value for the 'able to confirm claimants identity?' question
    And I progress to the Registration Identity Confirmation page
    And I complete the required case details fields
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected an answer to the confirm claimants identity question

  @Workflow @WCSRegression
  Scenario: User selects that they can confirm identity on the Registration Identity Confirmation page
    When I progress to the Registration Identity Confirmation page
    And I select that I can confirm the claimants identity
    Then the claim should be moved to the "Eligibility" stage

  @Navigation
  Scenario: User sends the claim to stage 1 identity check
    And I progress to the Registration Identity Confirmation page
    And I complete the required case details fields
    When I pass the claim to stage 1 identity check
    Then the "Registration Identity Stage 1" page should be displayed

  @Validation
  Scenario: User clicks Save Changes without selecting a value for the 'able to confirm claimants identity?' question
    And I progress to the Registration Identity Confirmation page
    And I complete the required case details fields
    And I pass the claim to stage 1 identity check
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected an answer to the confirm claimants identity question

  @Workflow @WCSRegression
  Scenario: User send a claim to Eligibility from Registration Identity Stage 1
    And I progress to the Registration Identity Confirmation page
    And I complete the required case details fields
    And I pass the claim to stage 1 identity check
    When I pass the claim to the Eligibility team
    Then the claim should be moved to the "Eligibility" stage

  @Workflow @WCSRegression
  Scenario: User sends the claim to Identity Rejected as they cannot confirm the claimants identity
    And I progress to the Registration Identity Confirmation page
    And I complete the required case details fields
    When I pass the claim to stage 1 identity check
    And I select that identity cannot be confirmed
    Then the claim should be moved to the "Identity Rejected" stage



