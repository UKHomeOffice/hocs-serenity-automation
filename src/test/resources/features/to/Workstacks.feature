@Workstacks @TO
Feature: Complaints Workstacks

  Background:
    Given I am logged into "CS" as user "TO_USER"

  @TORegression
  Scenario: Treat Official complaints user can see the required information when viewing the Treat Official workstack
    And I enter a "Treat Official UKVI" workstack
    Then the "Treat Official UKVI" workstack should contain only the expected columns