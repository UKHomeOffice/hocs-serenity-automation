@Prioritisation @MPAM
Feature: Prioritisation

  Background:
    Given I am logged into "CS" as user "MPAM_USER"

  @MPAMRegression2
  Scenario: User checks that a Ministerial case is given a higher priority in My Cases workstack than an Official case
    And I create and claim a MPAM case with "Standard" as the Urgency level and "Ministerial" as the Reference Type
    And I record the case reference of this "Ministerial" case
    And I create and claim a MPAM case with "Standard" as the Urgency level and "Official" as the Reference Type
    And I record the case reference of this "Official" case
    When I click to view the "My Cases" workstack
    Then the "Ministerial" case should be higher up the workstack than the "Official" case

  @MPAMRegression2
  Scenario: User checks that a Priority case is given a higher priority in a workstack than a Standard case
    And I create a MPAM case with "Priority" as the Urgency level and "Ministerial" as the Reference Type
    And I record the case reference of this "Priority" case
    And I create a MPAM case with "Standard" as the Urgency level and "Ministerial" as the Reference Type
    And I record the case reference of this "Standard" case
    When I view the MPAM cases in the appropriate "Triage" stage workstack
    Then the "Priority" case should be higher up the workstack than the "Standard" case

  @MPAMRegression2
  Scenario: User checks that a Immediate case is given a higher priority in a workstack than a Priority case
    And I create a MPAM case with "Immediate" as the Urgency level and "Ministerial" as the Reference Type
    And I record the case reference of this "Immediate" case
    And I create a MPAM case with "Priority" as the Urgency level and "Ministerial" as the Reference Type
    And I record the case reference of this "Priority" case
    When I view the MPAM cases in the appropriate "Triage" stage workstack
    Then the "Immediate" case should be higher up the workstack than the "Priority" case

  @MPAMRegression2
  Scenario: User checks that a case approaching its SLA is given a higher priority in a workstack than a case received today
    And I create a single "MPAM" case with the correspondence received date set 19 workdays ago
    And I record the case reference of this "Older" case
    And I create a single "MPAM" case with the correspondence received date set 0 workdays ago
    And I record the case reference of this "Newer" case
    When I view the MPAM cases in the appropriate "Creation" stage workstack
    Then the "Older" case should be higher up the workstack than the "Newer" case

  @MPAMRegression2
  Scenario: User checks that a case approaching its SLA is given a higher priority in a workstack than a case that has passed its SLA
    And I create a single "MPAM" case with the correspondence received date set 19 workdays ago
    And I record the case reference of this "Approaching SLA" case
    And I create a single "MPAM" case with the correspondence received date set 21 workdays ago
    And I record the case reference of this "Past SLA" case
    When I view the MPAM cases in the appropriate "Creation" stage workstack
    Then the "Approaching SLA" case should be higher up the workstack than the "Past SLA" case