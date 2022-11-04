@Workstacks @TO
Feature: Complaints Workstacks

  Background:
    Given I am logged into "CS" as user "TO_USER"

  @TORegression
  Scenario Outline: A Treat Official complaints user can see the required information when viewing the Treat Official workstack
    And I enter a "<workstack>" workstack
    Then the "<workstack>" workstack should contain only the expected columns
    Examples:
      | workstack               |
      | Treat Official UKVI     |
      | Treat Official BF       |
      | TREAT OFFICIAL MY CASES |