@COMPWorkstacks @COMP
Feature: COMP Workstacks

  Background:
    Given I am logged into "DECS" as user "COMP_USER"

  @Workstacks @COMPRegression
  Scenario Outline: COMP User sees the required information when viewing a workstack
    And I enter a "<workstack>" workstack
    Then the "<workstack>" workstack should contain only the expected columns
    Examples:
      | workstack   |
      | COMP Team   |
      | COMP Search |

  @COMPRegression
  Scenario: User is able to see a yellow highlighted deadline on an COMP case that is close to its SLA
    When I create a single "COMP" case with the correspondence received date set 15 workdays ago
    And I view the COMP case in the Complaint Registration workstack
    Then the case deadline should be highlighted yellow

  @COMPRegression
  Scenario: User is unable to see a red highlighted deadline on an COMP case that is past its deadline date
    When I create a single "COMP" case with the correspondence received date set 21 workdays ago
    And I view the COMP case in the Complaint Registration workstack
    Then the case deadline should be highlighted red