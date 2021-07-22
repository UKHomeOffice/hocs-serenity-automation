@COMPWorkstacks @COMP
Feature: COMP Workstacks

  Background:
    Given I am logged into "DECS" as user "COMP_USER"

#     HOCS-2865, HOCS-3161
  @COMPRegression
  Scenario: COMP User sees the required information when viewing a workstack
    Given I am logged into "DECS" as user "COMP_USER"
    And I click to view the "Complaint Registration" workstack
    Then the "Complaint Registration" workstack should contain only the expected columns

#     HOCS-3076 HOCS-3161
  @COMPRegression
  Scenario: User is able to see a yellow highlighted deadline on a COMP case that is close to its deadline date
    Given I am logged into "DECS" as user "COMP_USER"
    When I create a single "COMP" case with the correspondence received date set 15 workdays ago
    And I click to view the case in the "Complaint Registration" workstack
    Then the case deadline should be highlighted yellow

#     HOCS-3076 HOCS-3161
  @COMPRegression
  Scenario: User is able to see a red highlighted deadline on an COMP case that is past its deadline date
    Given I am logged into "DECS" as user "COMP_USER"
    When I create a single "COMP" case with the correspondence received date set 21 workdays ago
    And I click to view the case in the "Complaint Registration" workstack
    Then the case deadline should be highlighted red