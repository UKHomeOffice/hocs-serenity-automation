
Feature: Validation is present throughout system

  Background:
    Given I am user "EAMON"
    When I am on the "<string>" page


   Scenario: Create Bulk Cases correspondence selection is validated
    When the current user navigates to the "CREATE BULK CASES" page
    And they do not select a type of correspondence when creating a case
    Then an error message should be displayed informing the user that case type is required

   Scenario: When creating bulk MIN cases date received is required
    When the current user navigates to the "CREATE BULK CASES" page
    And they create the case without entering anything in the date received text boxes
    Then an error message should be displayed informing the user that date received is required


  Scenario: When creating bulk MIN cases a valid date must be entered
    When the current user navigates to the "CREATE BULK CASES" page
    And they create the case with an invalid date
    Then an error message should be displayed informing the user that the date is invalid


  Scenario: When creating bulk MIN cases documents are required
    When the current user navigates to the "CREATE BULK CASES" page
    And they create the bulk cases without adding a document
    Then an error message should be displayed informing the user that documents are mandatory


  Scenario: Date correspondence was sent must be entered at Data Input stage
    When the current user creates a single case "DCU MIN"
    And they don't enter the date correspondence was sent at the data input stage
    Then an error message should be displayed informing the user that correspondence date is required


  Scenario: How correspondence was received radio button must be selected at Data Input stage
    When the current user creates a single case "DCU MIN"
    And they don't select how correspondence was received radio button at the data input stage
    Then an error message should be displayed informing the user that how the correspondence was sent is required


  Scenario: User must add a primary correspondent at Data Input stage
    When the current user creates a single case "DCU MIN"
    And they do not add a primary correspondent at the data input stage
    Then an error message should be displayed informing the user the primary correspondent must be added


  Scenario: User must select correspondent type when adding a primary correspondent
    When the current user creates a single case "DCU MIN"
    And they allocate the case to themself
    And they complete the first data input screen
    And they click the continue button
    And they do not select


