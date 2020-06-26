@Prioritisation
Feature: Prioritisation

  Background:
    Given I log in to DECS

  Scenario: User checks that a Ministerial case is given a higher priority in My Cases workstack than an Offical case
    And I create and claim a MPAM case with "Standard" as the Urgency level and "Ministerial" as the Reference Type
    And I record the case reference of this case as "Ministerial"
    And I create and claim a MPAM case with "Standard" as the Urgency level and "Official" as the Reference Type
    And I record the case reference of this case as "Official"
    When I click to view the "My Cases" workstack
    Then the "Ministerial" case should be higher up the workstack than the "Official" case

  Scenario: User checks that a Priority case is given a higher priority in a workstack than a Standard case
    And I create a MPAM case with "Priority" as the Urgency level and "Ministerial" as the Reference Type
    And I record the case reference of this case as "Priority"
    And I create and claim a MPAM case with "Standard" as the Urgency level and "Ministerial" as the Reference Type
    And I record the case reference of this case as "Standard"
    When I view the MPAM case in the appropriate "Triage" stage workstack
    Then the "Priority" case should be higher up the workstack than the "Standard" case

  Scenario: User checks that a Immediate case is given a higher priority in a workstack than a Priority case
    And I create a MPAM case with "Immediate" as the Urgency level and "Ministerial" as the Reference Type
    And I record the case reference of this case as "Immediate"
    And I create and claim a MPAM case with "Priority" as the Urgency level and "Ministerial" as the Reference Type
    And I record the case reference of this case as "Priority"
    When I view the MPAM case in the appropriate "Triage" stage workstack
    Then the "Immediate" case should be higher up the workstack than the "Priority" case

  Scenario: User checks that a case approaching its SLA is given a higher priority in a workstack than a case recieved today
    And I create a single "MPAM" case with the correspondence received date set 19 workdays ago
    And I record the case reference of this case as "Older"
    And I create a single "MPAM" case with the correspondence received date set 0 workdays ago
    And I record the case reference of this case as "Newer"
    When I view the MPAM case in the appropriate "Creation" stage workstack
    Then the "Older" case should be higher up the workstack than the "Newer" case

  Scenario: User checks that  a case approaching its SLA is given a higher priority in a workstack than a case that has passed its SLA
    And I create a single "MPAM" case with the correspondence received date set 19 workdays ago
    And I record the case reference of this case as "Approaching SLA"
    And I create a single "MPAM" case with the correspondence received date set 21 workdays ago
    And I record the case reference of this case as "Past SLA"
    When I view the MPAM case in the appropriate "Creation" stage workstack
    Then the "Approaching SLA" case should be higher up the workstack than the "Past SLA" case


