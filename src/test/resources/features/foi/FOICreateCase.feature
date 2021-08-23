@FOICreateCase @FOI @FOIWorkflow
Feature: FOI Create Case

  Background:
    Given I am logged into "CS" as user "FOI_USER"
    When I navigate to the "Create Single Case" page
    And I select the FOI case type and continue

  #HOCS-3251, HOCS-3248, HOCS-3247, HOCS-3252
  Scenario: User is able to create an FOI Case
    And I select how the request was received
    And I enter the correspondent details
    And I select the "Animal alternatives (3Rs)" FOI topic
    And I enter the Request Question
    And I click the "Submit" button
    Then a case is created successfully
    And I navigate to the "Dashboard" page
    Then the case should be moved to the "Case Creation" stage
    And the case "Should" be allocated to me in the summary