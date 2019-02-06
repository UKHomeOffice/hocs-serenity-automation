Feature: Validation is present throughout system
  Background:
    Given I am user "EAMON"

  @Validation
  Scenario: Create Single Case correspondence selection is validated
    When the current user navigates to the "CREATE SINGLE CASE" page
    And they do not select a type of correspondence when creating a case
    Then an error message should be displayed informing the user that case type is required
