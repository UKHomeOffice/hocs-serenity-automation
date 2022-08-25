@Prioritisation @POGR
Feature: Prioritisation

  Background:
    Given I am logged into "CS" as user "POGR_USER"

  @COMPRegression
  Scenario: User checks that a POGR Priority case is given a higher priority in My Cases workstack than a POGR non-Priority  case
    And I create and claim a Priority POGR case
    And I record the case reference of this "Priority" case
    And I create and claim a non-Priority POGR case
    And I record the case reference of this "non-Priority" case
    When I click to view the "My Cases" workstack
    Then the "Priority" case should be higher up the workstack than the "non-Priority" case

  @COMPRegression
  Scenario: User checks that a Priority case is given a higher priority in a POGR team workstack than a non-Priority  case
    And I create a "HMPO" Priority POGR case
    And I record the case reference of this "Priority" case
    And I create a "HMPO" non-Priority POGR case
    And I record the case reference of this "non-Priority" case
    When I view the cases in the "HMPO Complaints" workstack
    Then the "Priority" case should be higher up the workstack than the "non-Priority" case

  @COMPRegression
  Scenario: User checks that a POGR case received before another is given higher priority in My Cases workstack
    And I create a single "POGR" case with the correspondence received date set 10 workdays ago
    And I record the case reference of this "Older" case
    And I create a single "MPAM" case with the correspondence received date set 5 workdays ago
    And I record the case reference of this "Newer" case
    When I click to view the "My Cases" workstack
    Then the "Older" case should be higher up the workstack than the "Newer" case