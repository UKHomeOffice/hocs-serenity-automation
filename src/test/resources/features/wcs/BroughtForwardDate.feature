@BroughtForwardDate @WCS
Feature: Brought Forward Date

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And  I create a single "WCS" claim
    And I move the claim to the "Casework" stage

  @Data
  Scenario: User enters a brought forward date and views it in case info
    When I fill in the case info including the Brought Forward Date: "17/10/2019"
    And I save changes to the claim
    And I navigate to the "Dashboard" page
    And I load the current claim
    And I open the case info accordion
    Then I look at the case info to see whether the brought forward date displays: "17/10/2019"

  @Data @WCSRegression
  Scenario: User enters a brought forward date and view it in read-only case info
    When I fill in the case info including the Brought Forward Date: "01/01/2001"
    And I save changes to the claim
    And I navigate to the "Dashboard" page
    And I click to view the claim in the "My Cases" workstack
    And I select the claim and unallocate it
    And I click the link for the current case in the workstack
    And I open the case details accordion
    Then I check that the read-only case details accordion displays "01/01/2001" as the brought forward date

  @Data @WCSRegression
  Scenario: User enters a brought forward date and progresses the claim and views the date is not present in case info
    When I fill in the case info including the Brought Forward Date: "01/01/2020"
    And I send the offer to QA
    And I load the current claim
    And I open the case details accordion
    Then I check that the read-only case details accordion displays "//" as the brought forward date
