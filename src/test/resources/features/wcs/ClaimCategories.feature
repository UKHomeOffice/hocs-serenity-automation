@ClaimCategories @WCS
Feature: Claim Categories

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I create a single "WCS" claim
    And I move the claim to the "Send Offer" stage
    And I open the claim categories accordion

  @Data
  Scenario: User inputs a value into amount awarded and amount paid in a claim category
    And I select the "BENEFITS" checkbox
    And I choose a full payment for the "BENEFITS" and enter 100 into the Amount Awarded field, and 100 into the Amount Paid field
    And I save changes to the claim
    And I open the claim categories accordion
    Then the Totals fields should display the correct amounts

  @Data @WCSRegression
  Scenario: User inputs values into every amount awarded, amount paid addition, additional amount paid and preliminary offer field in the claim category
  accordion and checks the totals
    And I select the "IMMIGRATION AND LEGAL FEES" checkbox
    And I choose a full payment for the "IMMIGRATION AND LEGAL FEES" and enter 5 into the Amount Awarded field, 100 into the Amount Paid field, and 1000 into the Additional Amount Paid field
    And I select the "DETENTION DEPORTATION AND REMOVAL" checkbox
    And I choose a full payment for the "DETENTION DEPORTATION AND REMOVAL" and enter 5 into the Amount Awarded field, and 100 into the Amount Paid field
    And I select the "EMPLOYMENT" checkbox
    And I choose a full payment for the "EMPLOYMENT" and enter 5 into the Amount Awarded field, 100 into the Amount Paid field, and 1000 into the Additional Amount Paid field
    And I select the "CHILD BENEFIT AND TAX CREDIT" checkbox
    And I choose a full payment for the "CHILD BENEFIT AND TAX CREDIT" and enter 5 into the Amount Awarded field, and 100 into the Amount Paid field
    And I select the "BENEFITS" checkbox
    And I choose a full payment for the "BENEFITS" and enter 5 into the Amount Awarded field, and 100 into the Amount Paid field
    And I select the "HOUSING" checkbox
    And I choose a full payment for the "HOUSING" and enter 5 into the Amount Awarded field, and 100 into the Amount Paid field
    And I select the "HEALTH" checkbox
    And I choose a full payment for the "HEALTH" and enter 5 into the Amount Awarded field, and 100 into the Amount Paid field
    And I select the "BANKING" checkbox
    And I choose a full payment for the "BANKING" and enter 5 into the Amount Awarded field, and 100 into the Amount Paid field
    And I select the "EDUCATION" checkbox
    And I choose a full payment for the "EDUCATION" and enter 5 into the Amount Awarded field, and 100 into the Amount Paid field
    And I select the "HOMELESSNESS" checkbox
    And I choose a full payment for the "HOMELESSNESS" and enter 5 into the Amount Awarded field, and 100 into the Amount Paid field
    And I select the "IMPACT ON DAILY LIFE" checkbox
    And I enter 5 into the Preliminary Offer field, and 100 into the Preliminary Offer Paid Amount field
    And I choose a full payment for the "IMPACT ON DAILY LIFE" and enter 5 into the Amount Awarded field, 100 into the Amount Paid field, and 1000 into the Additional Amount Paid field
    And I select the "DISCRETIONARY" checkbox
    And I choose a full payment for the "DISCRETIONARY" and enter 5 into the Amount Awarded field, and 100 into the Amount Paid field
    And I select the "DRIVING LICENCE" checkbox
    And I choose a full payment for the "DRIVING LICENCE" and enter 5 into the Amount Awarded field, and 100 into the Amount Paid field
    And I select the "URGENT EXCEPTIONAL PAYMENT" checkbox
    And I enter 5 into amount awarded and 100 into amount deducted for the Urgent Exceptional Payment
    And I save changes to the claim
    And I open the claim categories accordion
    Then the Totals fields should display the correct amounts

  @Data @WCSRegression
  Scenario: User inputs an amount awarded into a field then deletes it and checks if the total updates
    And I select the "BENEFITS" checkbox
    And I choose a full payment for the "BENEFITS" and enter 100 into the Amount Awarded field, and 100 into the Amount Paid field
    And I save changes to the claim
    And I open the claim categories accordion
    Then the Totals fields should display the correct amounts
    And I delete the "BENEFITS" amount awarded
    And I save changes to the claim
    Then the Totals fields should display the correct amounts

  @Data
  Scenario: User inputs an amount awarded into multiple claim categories and deletes them to see if the totals update
    And I select the "BENEFITS" checkbox
    And I select the "CHILD BENEFIT AND TAX CREDIT" checkbox
    And I choose a full payment for the "BENEFITS" and enter 10 into the Amount Awarded field, and 100 into the Amount Paid field
    And I choose a full payment for the "CHILD BENEFIT AND TAX CREDIT" and enter 100 into the Amount Awarded field, and 100 into the Amount Paid field
    And I save changes to the claim
    And I open the claim categories accordion
    Then the Totals fields should display the correct amounts
    And I delete the "BENEFITS" amount awarded
    And I delete the "CHILD BENEFIT AND TAX CREDIT" amount awarded
    And I save changes to the claim
    Then the Totals fields should display the correct amounts