@CloseCaseTelephone
Feature: Close Case (Telephone)

  Background:
    Given I log in to "DECS" as user "UKVI_USER"

  @UKVIRegression1 @ignore
  Scenario Outline: User closes a telephone case at different stages
    And I create a MPAM case  with "Official" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I select the Close Case Telephone radio button at the "<stage>" stage and confirm
    And I enter the mandatory information at the Close Case Telephone screen and close the case
    Then the case should be closed
    Examples:
    | stage             |
    | Triage            |
    | Draft             |
    | QA                |
    | Awaiting Dispatch |

  @Validation
  Scenario Outline: User tests the validation of the Close Case (Telephone) screen
    And I create a MPAM case  with "Official" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I select the Close Case Telephone radio button at the "<stage>" stage and confirm
    And I check the validation at the Close Case Telephone screen
    Examples:
      | stage             |
      | Triage            |
      | Draft             |
      | QA                |
      | Awaiting Dispatch |